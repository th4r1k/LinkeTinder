import path from "path";
import CopyPlugin from "copy-webpack-plugin";
import url from 'url';

const __filename = url.fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

  export default{
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
    new CopyPlugin({
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
      },
    ],
  },
};
