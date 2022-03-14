
module.exports = {
    // chainWebpack: config => {
    //     config.resolve.alias.set('@', resolve('src')).set('_com', resolve('src/components'));

    // },
    publicPath: './',
    devServer: {
        host: '0.0.0.0',
        port: 8081,
        proxy: {
            '/example': {
                target: 'http://localhost:8081/example/',
                changeOrigin: true,
                pathRewrite: {
                    "^/example": ""
                }
            },
        }
    }
}