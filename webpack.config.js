const path = require("path")
const HtmlWebpackPlugin = require("html-webpack-plugin")

module.exports = {
    devtool: "source-map",
    entry: [
        "babel-polyfill",
        "./src/main/react/index.jsx"
    ],
    output: {
        path: path.resolve(__dirname, "build/resources/main/static"),
        filename: "[name].bundle-[hash].js",
        chunkFilename: "[name].bundle-[hash].js",
        publicPath: "/"
    },
    module: {
        rules: [
            {
                test: /\.js|.jsx$/,
                exclude: /node_modules/,
                loader: "babel-loader"
            },
            {
                test: /\.scss$/,
                loader: "style-loader!css-loader!sass-loader!resolve-url-loader!sass-loader?sourceMap"
            },
            {
                test: /\.css$/,
                loader: "style-loader!css-loader"
            },
            {
                test: /\.(gif|jpg|png|svg|eot|otf|ttf|woff(2)?)(\?[^]*)?$/,
                use: [
                    {
                        loader: "file-loader"
                    }
                ]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            inject: false,
            template: require("html-webpack-template"),
            title: "React",
            appMountId: "application"
        })
    ]
}
