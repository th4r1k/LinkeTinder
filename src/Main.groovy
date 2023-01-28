import Utils.Navigate
import Utils.Populate

static void main(String[] args) {
  Populate.candidates();
  Populate.enterprises();
  Navigate.start()
}