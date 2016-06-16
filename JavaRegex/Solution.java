// https://www.hackerrank.com/challenges/java-regex/submissions/code/22029014

class myRegex {
    private String numSegment = "([01]?\\d{1,2}|2[01234]\\d|25[0-5])";
    public String pattern = numSegment + "\\." + numSegment + "\\." + numSegment + "\\." + numSegment;
}
