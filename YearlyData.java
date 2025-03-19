public class YearlyData {
    private int year;
    private Baby[] boys;
    private Baby[] girls;
    
    public YearlyData(int year, Baby[] boys, Baby[] girls) {
        this.year = year;
        this.boys = boys;
        this.girls = girls;
    }
    
    public int getYear() {
        return year;
    }
    
    public Baby[] getBoys() {
        return boys;
    }
    
    public Baby[] getGirls() {
        return girls;
    }
}

