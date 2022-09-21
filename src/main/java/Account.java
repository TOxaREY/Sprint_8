public class Account {

    private final String name;

    public Account(String name) {
        this.name = name;
    }

    public boolean checkNameToEmboss() {
       if (name.length() >= 3 && name.length() <= 19) {
           return name.equals(name.trim()) &&
                   (name.trim().length() - name.trim().replaceAll(" ", "").length()) == 1;
       } else {
           return false;
       }
    }
}
