void main() {
    Date date = new Date(7, 10, 2026);

    IO.println(date.toString());
    IO.println(date.before(new Date(12, 06, 2012)));
    IO.println(date.daysSinceBeginYear());
    IO.println(date.daysUntilEndYear());
    IO.println(date.daysBetween(new Date(7, 11, 2026)));
}