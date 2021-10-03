package org.dfpl.lecture.database.assignment.assignment17011685;

public class BicycleLoad {
    private final String name;
    private final String type;
    private final String startPoint;
    private final String endPoint;
    private final String stopoverPoint;
    private final Boolean isTwoWay;
    private final Double length;
    private final Double normalWidth;
    private final Double bicycleWidth;
    private final Integer installYear;
    private final String texture;

    public BicycleLoad(String name, String type, String startPoint, String endPoint, String stopoverPoint, Boolean isTwoWay, Double length, Double normalWidth, Double bicycleWidth, Integer installYear, String texture) {
        this.name = name;
        this.type = type;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.stopoverPoint = stopoverPoint;
        this.isTwoWay = isTwoWay;
        this.length = length;
        this.normalWidth = normalWidth;
        this.bicycleWidth = bicycleWidth;
        this.installYear = installYear;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getStopoverPoint() {
        return stopoverPoint;
    }

    public Boolean getTwoWay() {
        return isTwoWay;
    }

    public Double getLength() {
        return length;
    }

    public Double getNormalWidth() {
        return normalWidth;
    }

    public Double getBicycleWidth() {
        return bicycleWidth;
    }

    public Integer getInstallYear() {
        return installYear;
    }

    public String getTexture() {
        return texture;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", type='" + type + '\'' +
                        ", startPoint='" + startPoint + '\'' +
                        ", endPoint='" + endPoint + '\'' +
                        ", stopoverPoint='" + stopoverPoint + '\'' +
                        ", isTwoWay=" + isTwoWay +
                        ", length=" + length +
                        ", normalWidth=" + normalWidth +
                        ", bicycleWidth=" + bicycleWidth +
                        ", installYear=" + installYear +
                        ", texture='" + texture + '\'' +
                        "\n";
    }

}
