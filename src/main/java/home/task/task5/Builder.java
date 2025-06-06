package home.task.task5;

public class Builder {
    private String foundationType;
    private String wallMaterial;
    private String roofMaterial;
    private boolean hasStoneOven;
    private boolean hasCellar;

    private Builder(HouseBuilder homeBuilder) {
        this.foundationType = homeBuilder.foundationType;
        this.wallMaterial = homeBuilder.wallMaterial;
        this.roofMaterial = homeBuilder.roofMaterial;
        this.hasStoneOven = homeBuilder.hasStoneOven;
        this.hasCellar = homeBuilder.hasCellar;
    }

    @Override
    public String toString() {
        return "Builder{" +
                "foundationType='" + foundationType + '\'' +
                ", wallMaterial='" + wallMaterial + '\'' +
                ", roofMaterial='" + roofMaterial + '\'' +
                ", hasStoneOven=" + hasStoneOven +
                ", hasCellar=" + hasCellar +
                '}';
    }

    public static class HouseBuilder {
        private String foundationType;
        private String wallMaterial;
        private String roofMaterial;
        private boolean hasStoneOven;
        private boolean hasCellar;

        public HouseBuilder(String foundationType, String wallMaterial, String roofMaterial) {
            this.foundationType = foundationType;
            this.wallMaterial = wallMaterial;
            this.roofMaterial = roofMaterial;
        }

        public HouseBuilder setHasStoneOven(boolean hasStoneOven) {
            this.hasStoneOven = hasStoneOven;
            return this;
        }

        public HouseBuilder setHasCellar(boolean hasCellar) {
            this.hasCellar = hasCellar;
            return this;
        }

        public Builder build() {
            return new Builder(this);
        }
    }
}

class BuilderTest {
    public static void main(String[] args) {
        Builder house = new Builder.HouseBuilder("Ленточный", "Дерево", "Ондулин")
                .setHasStoneOven(true)
                .build();
        System.out.println(house);
    }
}
