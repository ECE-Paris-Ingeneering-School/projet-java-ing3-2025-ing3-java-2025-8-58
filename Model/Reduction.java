package Model;

public class Reduction {
    private int idReduction;
    private String nomReduction;
    private String pourcentageReduction;
    private int typeReduction;

    // Constructeurs
    public Reduction() {}

    public Reduction(String nomReduction, String pourcentageReduction, int typeReduction) {
        this.nomReduction = nomReduction;
        this.pourcentageReduction = pourcentageReduction;
        this.typeReduction = typeReduction;
    }

    // Getters et Setters
    public int getIdReduction() {
        return idReduction;
    }

    public void setIdReduction(int idReduction) {
        this.idReduction = idReduction;
    }

    public String getNomReduction() {
        return nomReduction;
    }

    public void setNomReduction(String nomReduction) {
        this.nomReduction = nomReduction;
    }

    public String getPourcentageReduction() {
        return pourcentageReduction;
    }

    public void setPourcentageReduction(String pourcentageReduction) {
        this.pourcentageReduction = pourcentageReduction;
    }

    public int getTypeReduction() {
        return typeReduction;
    }

    public void setTypeReduction(int typeReduction) {
        this.typeReduction = typeReduction;
    }

    @Override
    public String toString() {
        return "Reduction{" +
                "idReduction=" + idReduction +
                ", nomReduction='" + nomReduction + '\'' +
                ", pourcentageReduction='" + pourcentageReduction + '\'' +
                ", typeReduction=" + typeReduction +
                '}';
    }
}
