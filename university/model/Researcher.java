package university.model;

import java.util.List;

public interface Researcher {

    List<ResearchPaper> getPapers();
    void addPaper(ResearchPaper p);

    default int getHIndex() {
        List<ResearchPaper> papers = getPapers();
        if (papers.isEmpty()) return 0;
        int[] c = papers.stream().mapToInt(ResearchPaper::getCitations).sorted().toArray();
        int h = 0, n = c.length;
        for (int i = 0; i < n; i++) h = Math.max(h, Math.min(n - i, c[i]));
        return h;
    }

    default void printPapers() {
        System.out.printf("  H-Index: %d  |  Papers: %d%n", getHIndex(), getPapers().size());
        getPapers().forEach(p -> System.out.println("   • " + p));
    }
}
