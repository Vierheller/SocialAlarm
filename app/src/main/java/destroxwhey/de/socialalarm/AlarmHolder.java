package destroxwhey.de.socialalarm;

/**
 * Created by Jan-Niklas on 13.11.2014.
 */
public class AlarmHolder {
    private String name;
    private int hour;
    private int minute;
    private int requestcode;
    private int Mo;
    private int Tu;
    private int We;
    private int Th;
    private int Fr;
    private int Sa;
    private int Su;
    private int[] days = new int[7];

    public AlarmHolder(String name, int hour, int minute, int requestcode, int mo, int tu, int we, int th, int fr, int sa, int su) {
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.requestcode = requestcode;
        this.Mo = mo;
        this.Tu = tu;
        this.We = we;
        this.Th = th;
        this.Fr = fr;
        this.Sa = sa;
        this.Su = su;
        this.days[0]=mo;this.days[1]=tu;this.days[2]=we;this.days[3]=th;this.days[4]=fr;this.days[5]=sa;this.days[6]=su;
    }

    public String getName() {
        return name;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getRequestcode() {
        return requestcode;
    }

    public int getMo() {
        return Mo;
    }

    public int getTu() {
        return Tu;
    }

    public int getWe() {
        return We;
    }

    public int getTh() {
        return Th;
    }

    public int getFr() {
        return Fr;
    }

    public int getSa() {
        return Sa;
    }

    public int getSu() {
        return Su;
    }

    public int[] getDays() {
        return days;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setRequestcode(int requestcode) {
        this.requestcode = requestcode;
    }

    public void setMo(int mo) {
        Mo = mo;
    }

    public void setTu(int tu) {
        Tu = tu;
    }

    public void setWe(int we) {
        We = we;
    }

    public void setTh(int th) {
        Th = th;
    }

    public void setFr(int fr) {
        Fr = fr;
    }

    public void setSa(int sa) {
        Sa = sa;
    }

    public void setSu(int su) {
        Su = su;
    }
}
