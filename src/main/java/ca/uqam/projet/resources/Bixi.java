package ca.uqam.projet.resources;

import org.w3c.dom.Element;

public class Bixi {

    private final int id;
    private final String name;
    private final float x;
    private final float y;
    private final boolean ouvert;
    private final int veloDisponible;
    private final int emplacementDisponible;

    public Bixi(Element element) {
        this.name = element.getElementsByTagName("name").item(0).getTextContent();
        this.id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
        this.ouvert = !Boolean.parseBoolean(element.getElementsByTagName("locked").item(0).getTextContent())
                && Boolean.parseBoolean(element.getElementsByTagName("installed").item(0).getTextContent());
        this.x = Float.parseFloat(element.getElementsByTagName("long").item(0).getTextContent());
        this.y = Float.parseFloat(element.getElementsByTagName("lat").item(0).getTextContent());
        this.veloDisponible = Integer.parseInt(element.getElementsByTagName("nbBikes").item(0).getTextContent());
        this.emplacementDisponible = Integer.parseInt(element.getElementsByTagName("nbEmptyDocks").item(0).getTextContent());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isOuvert() {
        return ouvert;
    }

    public int getVeloDisponible() {
        return veloDisponible;
    }

    public int getEmplacementDisponible() {
        return emplacementDisponible;
    }

}
