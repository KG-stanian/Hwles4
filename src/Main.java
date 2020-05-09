import java.util.Random;

public class Main {
// Добавить 4-го игрока Medic, у которого есть способность лечить
// после каждого раунда на N-ное количество единиц здоровья только одного из членов команды,
// имеющего здоровье менее 100 единиц. Мертвых героев медик оживлять не может, и лечит он до тех пор пока жив сам.
// Медик не участвует в бою, но получает урон от Босса

//ДЗ на сообразительность:
//Добавить n-го игрока, Golem, который имеет увеличенную жизнь но слабый удар.
// Может принимать на себя 1/3 часть урона исходящего от босса по другим игрокам.
//Добавить n-го игрока, Lucky, имеет шанс уклонения от ударов босса.
//Добавить n-го игрока, Berserk, блокирует часть удара босса по себе
// и прибавляет заблокированный урон к своему урону и возвращает его боссу
//Добавить n-го игрока, Thor, удар по боссу имеет шанс оглушить босса на 1 раунд,
// вследствие чего босс пропускает 1 раунд и не наносит урон героям.
    public static int bossHealth = 750;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 250, 240, 100}; // 0, 5, 0
    public static int[] heroesDamage = {20, 15, 25, 0};
    public static String[] heroesSuperAbilities = {"Physical", "Magical", "Kinetic", "Medical"};
    public static int roundNumber = 1;

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesSuperAbilities.length); // 0, 1, 2
        bossDefenceType = heroesSuperAbilities[randomIndex];
    }

    public static void main(String[] args) {
        printStatistics();
        System.out.println("######################");
        while (!isGameFinished()) {
            System.out.println("Round " + roundNumber);
            round();
        }
    }

    public static void round() {
        changeBossDefence();
        bossHit();
        medicHit();
        heroesHit();
        printStatistics();
        roundNumber++;
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true; // предполагаем что все герои мертвы
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("______________________________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesSuperAbilities[i] + " health: " + heroesHealth[i]);
        }
        System.out.println("______________________________");
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (bossDefenceType == heroesSuperAbilities[i]) {
                    Random r = new Random();
                    int coef = r.nextInt(10);
                    System.out.println(heroesSuperAbilities[i] + " hit boss with critical damage "
                            + heroesDamage[i] * coef);
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0) {
                if (heroesHealth[i] > 0) {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }
                }
            }
        }
    }

    public static void medicHit() {
        if (heroesHealth.length  < 100){
            Random r = new Random();
            int coef = r.nextInt(50);
        }

    }

}
