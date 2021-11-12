/* config-overrides.js */
module.exports = function override(config, env) {
    //do stuff with the webpack config...
    config.externals = {'config': JSON.stringify({
        apiUrl: 'http://localhost:8080',
        httpsUrl: 'https://localhost:8483',
        imgUrl: 'http://202.120.40.106:9090',
        zuulUrl: 'http://localhost:8083',
    })};
    return config;
};