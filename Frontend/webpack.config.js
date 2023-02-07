const path = require("path");
const copyPlugin = require("copy-webpack-plugin");

module.exports = {
  // mode: "none",
  mode: "production",
  entry: [
    "./src/app.ts",
    "./src/Entity/User.ts",
    "./src/Entity/Candidate.ts",
    "./src/Entity/Enterprise.ts",
    "./src/Data/Candidates.ts",
    "./src/Data/Enterprises.ts",
  ],
  output: {
    filename: "app.min.js",
    path: path.join(__dirname, "dist"),
  },
  plugins: [
    new copyPlugin({
      patterns: [{ from: "public" }],
    }),
  ],
  resolve: {
    extensions: [".ts", ".js"],
  },
  module: {
    rules: [
      {
        test: /\.ts$/,
        use: "ts-loader",
        exclude: /node_modules/,
      },
    ],
  },
};
