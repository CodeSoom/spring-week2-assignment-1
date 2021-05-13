const path = require('path');

module.exports = {
  entry: path.resolve(__dirname, 'src/index.js'),
  resolve: {
    extensions: [".js", ".marko"]
  },
  module: {
    rules: [
      {
        test: /\.marko$/,
        loader: "@marko/webpack/loader"
      }
    ]
  },
  devServer: {
    port: 3000,
  },
};
