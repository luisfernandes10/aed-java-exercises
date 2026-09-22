public class Date {
    private final int month;
    private final int day;
    private final int year;

    private final int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Date(int month, int day, int year) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Mês inválido.");
        if (day < 1 || day > MONTH_DAYS[month - 1])
            throw new IllegalArgumentException("Dia inválido.");

        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    public String toString() {
        return Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
    }

    public boolean before(Date other) {
        if (this.year != other.year)
            return this.year < other.year;
        if (this.month != other.month)
            return this.month < other.month;
        return this.day < other.day;
    }

    public int daysSinceBeginYear() {
        int sum = 0;
        for (int i = 0; i < month - 1; i++)
            sum += MONTH_DAYS[i];
        sum += day;
        return sum;
    }

    public int daysUntilEndYear() {
        return 365 - daysSinceBeginYear();
    }

    public int daysBetween(Date other) {
        int totalDaysThis = this.year * 365 + this.daysSinceBeginYear();
        int totalDaysOther = other.year * 365 + other.daysSinceBeginYear();
        return Math.abs(totalDaysThis - totalDaysOther);
    }
}
