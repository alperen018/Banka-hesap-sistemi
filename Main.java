import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Bankaccount> hesaplar = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int seçim;


        do {
            System.out.println("======== BANKA SİSTEMİ ========");
            System.out.println("1 - Hesap Oluştur");
            System.out.println("2 - Hesapları Listele");
            System.out.println("3 - Para Yatır");
            System.out.println("4 - Para Çek");
            System.out.println("5 - Para Transfer Et");
            System.out.println("6 - Hesap Ara");
            System.out.println("0 - Çıkış");
            System.out.print("Seçiminiz: ");
            seçim = scanner.nextInt();

            switch (seçim) {
                case 1:
                    hesapOlustur();
                    break;
                case 2:
                    hesaplariListele();
                    break;
                case 3:
                    paraYatirIslemi();
                    break;
                case 4:
                    paraCekIslemi();
                    break;
                case 5:
                    transferYap();
                    break;
                case 6:
                    hesapAra();
                    break;
                case 0:
                    System.out.println("Çıkıs Seçildi");
                    break;
                default:
                    System.out.println("geçersiz İşlem");
            }
        } while (seçim != 0);
    }

    static void hesapOlustur() {
        scanner.nextLine();
        System.out.print("Ad soyad: ");
        String name = scanner.nextLine();

        System.out.print("Hesap Numarası: ");
        int hesapno = scanner.nextInt();
        if (hesapBul(hesapno) != null) {
            System.out.println("Bu hesap numarası zaten kullanılmakts.");
            return;
        }
        System.out.print("Başlangıç Bakiyesi:");
        double bakiye = scanner.nextDouble();


        if (bakiye< 0) {
            System.out.println("!!Başlangıç Bakiyesi Negatif Olamaz!!");
            return;

        }
        Bankaccount yeniHesap = new Bankaccount(name, hesapno, true, bakiye);
        hesaplar.add(yeniHesap);
        System.out.println("Hesap oluşturuldu.");

    }

    static void hesaplariListele() {
        if (hesaplar.isEmpty()) {
            System.out.println("Sistemde hiç hesap yok.");
        } else {
            for (Bankaccount hesap : hesaplar) {
                hesap.bilgiYazdir();

            }
        }
    }
    static Bankaccount hesapBul(int no) {
        for (Bankaccount hesap : hesaplar) {
            if (hesap.accountno == no) {
                return hesap;
            }
        }
        return null;
    }

    static void paraYatirIslemi() {
        System.out.print("Hesap Numarası: ");
        int no = scanner.nextInt();
        Bankaccount hesap = hesapBul(no);

        if (hesap == null) {
            System.out.println("Hesap bulunamadı.");
            return;
        }
        if (!hesap.hesapaktifliği) {
            System.out.println("Hesap aktif değil.");
            return;
        }

        System.out.print("Yatırılcak Tutar: ");
        double tutar = scanner.nextDouble();
        if (tutar <= 0) {
            System.out.println("Tutar 0 dan büyük olmalı.");
            return;
        }

        hesap.ParaYatir(tutar);
        System.out.printf("İşlem başarılı. Güncel bakiye: %.2f %n", hesap.bakiye);


    }

    static void paraCekIslemi() {
        System.out.print("Hesap Numarası: ");
        int no = scanner.nextInt();
        Bankaccount hesap = hesapBul(no);

        if (hesap == null) {
            System.out.println("Hesap bulunamadı.");
            return;
        }
        if (!hesap.hesapaktifliği) {
            System.out.println("Hesap Pasif.");
            return;
        }

        System.out.print("Çekilecek Tutar: ");
        double tutar = scanner.nextDouble();
        if (tutar <= 0) {
            System.out.println("Tutar sıfırdan büyük olmalı.");
            return;
        }
        if (hesap.bakiye < tutar) {
            System.out.println("Yetersiz bakiywe.");
            return;
        }

        hesap.ParaCek(tutar);
        System.out.printf("İşlem başarılı. Kalan bakiye: %.2f %n", hesap.bakiye);
    }


    static void hesapAra() {
        System.out.print("Aranan Hesap Numarası: ");
        int no = scanner.nextInt();
        Bankaccount hesap = hesapBul(no);

        if (hesap == null) {
            System.out.println("Bu numarada bir hesap bulunamadı.");
        } else {
            hesap.bilgiYazdir();
        }
    }

    static void transferYap() {
        System.out.print("Gönderen Hesap: ");
        int gonderenNo = scanner.nextInt();
        System.out.print("Alıcı Hesap: ");
        int aliciNo = scanner.nextInt();
        System.out.print("Transfer Tutarı: ");
        double tutar = scanner.nextDouble();

        Bankaccount gonderen = hesapBul(gonderenNo);
        Bankaccount alici = hesapBul(aliciNo);

        if (gonderen == null || alici == null ) {
            System.out.println("Hesaplardan biri bulunamadı. ");
            return;
        }
        if (!gonderen.hesapaktifliği || !alici.hesapaktifliği) {
            System.out.println("Hesaplardan biri aktif değil.");
            return;
        }
        if (gonderenNo == aliciNo) {
            System.out.println("Gönderen  ve alıcı aynı hesap olamaz.");
            return;
        }
        if (tutar <= 0) {
            System.out.println("Tutar sıfırdan büyük olmalı.");
            return;
        }
        if (gonderen.bakiye < tutar) {
            System.out.println("Yetersiz bakiye.");
            return;
        }

        gonderen.ParaCek(tutar);
        alici.ParaYatir(tutar);
        System.out.println("Transfer başarıl ı.");
        System.out.printf("Gönderen Yeni Bakiye: %.2f %n", gonderen.bakiye);
        System.out.printf("Alıcı Yeni Bakiye: %.2f %n", alici.bakiye);
    }


}

/* THE END */
