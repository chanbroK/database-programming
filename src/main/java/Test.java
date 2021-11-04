import java.util.Comparator;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        class Email {
            private Integer from;
            private Integer to;

            Email(Integer from, Integer to) {
                setFrom(from);
                setTo(to);
            }

            public Integer getFrom() {
                return from;
            }

            public void setFrom(Integer from) {
                this.from = from;
            }

            public Integer getTo() {
                return to;
            }

            public void setTo(Integer to) {
                this.to = to;
            }

            @Override
            public String toString() {
                return "[" + from + "," + to + "]";
            }
        }
        LinkedList<Email> linkedList = new LinkedList<>();
        linkedList.add(new Email(5, 2));
        linkedList.add(new Email(0, 1));
        linkedList.add(new Email(3, 4));
        linkedList.sort(new Comparator<Email>() {
            @Override
            public int compare(Email o1, Email o2) {
                // TODO Auto-generated method stub
                if (o1.getFrom() > o2.getFrom())
                    return +1;
                else if (o1.getFrom() == o2.getFrom())
                    return 0;
                else
                    return -1;
            }
        });
        System.out.println(linkedList);
    }
}
