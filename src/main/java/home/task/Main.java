package home.task;

import home.task.module1.CustomHashMap;

public class Main {
    public static void main(String[] args) {
        CustomHashMap<String, String> map = new CustomHashMap<>();

        System.out.println(map.put("AB", "S"));
        System.out.println(map.put("BB", "J"));
        System.out.println(map.put("cc", "K"));
        System.out.println(map.put("v", "m"));

        System.out.println(map.put("ооо", "m"));
        System.out.println(map.put("мммм", "пп"));
        System.out.println(map.put("смч", "чм"));
        System.out.println(map.put("цц", "ууу"));
        System.out.println(map.put("ипа", "m"));
        System.out.println(map.put("чмва", "m"));
        System.out.println(map.put("уее5", "m"));
        System.out.println(map.put("тиьо", "m"));
        System.out.println(map.put("гшгщг", "m"));

        System.out.println();

        System.out.println(map.put(null, "m"));
        System.out.println(map.put(null, "v"));

        System.out.println(map.get("цц"));
        System.out.println(map.get(null));

        System.out.println(map.remove("цц"));
        System.out.println(map.remove("BB"));
        System.out.println(map.remove(null));
        System.out.println(map.remove("AB"));
        System.out.println(map.remove("PPPPPPPPPPPPPPPPPPP"));
        System.out.println();
    }
}
