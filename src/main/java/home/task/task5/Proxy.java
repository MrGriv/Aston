package home.task.task5;

interface DataBase {
    String download();
}

class Repository implements DataBase {
    private String filter;

    public Repository(String name) {
        this.filter = name;
    }

    @Override
    public String download() {
        return "Данные загружены: " + filter;
    }
}

public class Proxy implements DataBase {
    private Repository repository;
    private String filter;

    public Proxy(String name) {
        this.filter = name;
    }

    @Override
    public String download() {
        if (repository == null) {
            repository = new Repository(filter);
        }

        return repository.download();
    }
}

class TestProxy {
    public static void main(String[] args) {
        Proxy proxy = new Proxy("Иван");
        System.out.println(proxy.download());
        System.out.println(proxy.download());
    }
}
