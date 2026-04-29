package university.model;

public class ResearchPaper {
    private final String title, author, journal;
    private int citations;

    public ResearchPaper(String title, String author, String journal, int citations) {
        this.title = title; this.author = author;
        this.journal = journal; this.citations = citations;
    }

    public String getTitle()     { return title; }
    public String getAuthor()    { return author; }
    public String getJournal()   { return journal; }
    public int    getCitations() { return citations; }
    public void   cite()         { citations++; }

    @Override public String toString() {
        return String.format("'%s' — %s [%s] %d citations", title, author, journal, citations);
    }
}
