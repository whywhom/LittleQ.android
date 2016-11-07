package littleq.mammoth.com.littleq.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016/11/4.
 */
public class CourseScoreModel {
    private String description;
    private ArrayList<Double> highScores;
    private ArrayList<Double> levelScores;
    private ArrayList<Double> lowScores;
    private ArrayList<String> xRawDatas;

    private int maxValue;
    private int averageValue;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Double> getHighScores() {
        return highScores;
    }

    public void setHighScores(ArrayList<Double> highScores) {
        this.highScores = highScores;
    }

    public ArrayList<Double> getLevelScores() {
        return levelScores;
    }

    public void setLevelScores(ArrayList<Double> levelScores) {
        this.levelScores = levelScores;
    }

    public ArrayList<Double> getLowScores() {
        return lowScores;
    }

    public void setLowScores(ArrayList<Double> lowScores) {
        this.lowScores = lowScores;
    }

    public ArrayList<String> getxRawDatas() {
        return xRawDatas;
    }

    public void setxRawDatas(ArrayList<String> xRawDatas) {
        this.xRawDatas = xRawDatas;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(int averageValue) {
        this.averageValue = averageValue;
    }
}
