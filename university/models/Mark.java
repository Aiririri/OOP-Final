package university.model;

public class Mark {
    private final double att1, att2, finalExam;
    private final String teacher;

    public Mark(double att1, double att2, double finalExam, String teacher) {
        if (att1 < 0 || att1 > 30)           throw new IllegalArgumentException("ATT1 must be 0-30");
        if (att2 < 0 || att2 > 30)           throw new IllegalArgumentException("ATT2 must be 0-30");
        if (finalExam < 0 || finalExam > 40) throw new IllegalArgumentException("Final must be 0-40");
        this.att1 = att1; this.att2 = att2; this.finalExam = finalExam; this.teacher = teacher;
    }

    public double getAtt1()      { return att1; }
    public double getAtt2()      { return att2; }
    public double getFinalExam() { return finalExam; }
    public double getTotal()     { return att1 + att2 + finalExam; }
    public boolean passed()      { return getTotal() >= 50; }

    public double toGradePoint() {
        double t = getTotal();
        if (t >= 90) return 4.0; if (t >= 80) return 3.0;
        if (t >= 70) return 2.0; if (t >= 60) return 1.0; return 0.0;
    }

    public String letterGrade() {
        double t = getTotal();
        if (t >= 90) return "A"; if (t >= 80) return "B";
        if (t >= 70) return "C"; if (t >= 60) return "D"; return "F";
    }

    @Override public String toString() {
        return String.format("ATT1=%.1f  ATT2=%.1f  Final=%.1f  Total=%.1f (%s) by %s",
                att1, att2, finalExam, getTotal(), letterGrade(), teacher);
    }
}
