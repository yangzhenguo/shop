const fs = require('fs');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin')

const entrys = fs.readdirSync('./src/pages').map(chunk => ({[chunk]: `./src/pages/${chunk}/js/index.js`})).reduce((a, b)=>(Object.assign(a, b)), ({}));

module.exports = {
    mode: 'development',
    entry: entrys,
    output: {
        path: path.resolve(__dirname, '../web/src/main/webapp/assets/js'),
        filename: '[name]-[contenthash:8].js',
    },
    // 模块引用配置
    resolve: {
        // 定义模块查找的后缀，方便在代码引用时可省略后缀
        extensions: ['.js', '.vue', '.json'],
        // 定义引用路径别名 配置别名可以加快webpack查找模块的速度
        alias: {
            'vue$': 'vue/dist/vue.esm.js',
            '@': path.resolve('src'),
        }
    },
    module: {
        rules: [{
            test: /\.js$/,
            use: {
                loader: 'babel-loader',
                options: {
                    presets: [
                        ['env',{
                            targets: {
                                browsers: ['> 1%', 'last 2 versions']
                            }
                        }]
                    ]
                }
            },
            exclude: '/node_modules/'
        }, {
            test: /\.css$/,
            use: [
                'style-loader',
                'css-loader'
            ]
        }, {
            test: /\.vue$/,
            use: 'vue-loader'
        }]
    },
    plugins: [
        new VueLoaderPlugin(),
        ...Object.keys(entrys).map(chunkName => new HtmlWebpackPlugin({
            title: '${title}',
            'meta': {
                'viewport': 'width=device-width, initial-scale=1, shrink-to-fit=no',
                'theme-color': '#4285f4'
            },
            template: 'template.html',
            chunks: [chunkName],
            minify: {
                preserveLineBreaks: true
            },
            filename: path.resolve('../web/src/main/webapp/dist/', `${chunkName}.jsp`)
        }))
    ]
}