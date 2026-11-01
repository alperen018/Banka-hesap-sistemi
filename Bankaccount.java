public class Bankaccount {

    String name;
    int accountno;
    boolean hesapaktifliği;
    double bakiye;

    public Bankaccount(String name, int accountno, boolean hesapaktifliği, double bakiye) {

        this.name = name;
        this.accountno = accountno;
        this.hesapaktifliği = hesapaktifliği;
        this.bakiye = bakiye;
    }

    public void ParaCek(double amount) {
        if (amount > 0 && bakiye >= amount) {
            bakiye = bakiye - amount;

        }

    }

    public void ParaYatir(double amount) {
        if (amount > 0) {
            bakiye = bakiye + amount;

        }
    }

    public void bilgiYazdir() {
        String durum;
        if (hesapaktifliği) {
            durum = "Aktif";
        } else {
            durum = "Pasif";
        }

        System.out.printf("%s, %d, %b, %.2f,",name, accountno, hesapaktifliği, bakiye);

        //%d = int
/*%s = String
*%.2f = double
%b= boolean
* %d = int
*/


    }
}
