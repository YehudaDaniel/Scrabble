package server;


import java.util.Objects;

public class Tile {
    public final char letter;
    public final int score;

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    public static class Bag {
        int[] arr;
        Tile[] tiles;
        int[] original;
        private static Bag item = null;

        private Bag() {
            this.arr = new int[]{9, 2, 2, 4, 12,2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1,6, 4, 6, 4, 2, 2,1, 2, 1};
            this.original=this.arr.clone();
            this.tiles = new Tile[]{
                    new Tile('A', 1),
                    new Tile('B', 3),
                    new Tile('C', 3),
                    new Tile('D', 2),
                    new Tile('E', 1),
                    new Tile('F', 4),
                    new Tile('G', 2),
                    new Tile('H', 4),
                    new Tile('I', 1),
                    new Tile('J', 8),
                    new Tile('K', 5),
                    new Tile('L', 1),
                    new Tile('M', 3),
                    new Tile('N', 1),
                    new Tile('O', 1),
                    new Tile('P', 3),
                    new Tile('Q', 10),
                    new Tile('R', 1),
                    new Tile('S', 1),
                    new Tile('T', 1),
                    new Tile('U', 1),
                    new Tile('V', 4),
                    new Tile('W', 4),
                    new Tile('X', 8),
                    new Tile('Y', 4),
                    new Tile('Z', 10)
            };
        }

        public Tile getRand() {
            int sum=0;
            for(int i=0; i<26; i++)
                sum+=this.arr[i];
            if(sum==0)
                return null;
            int num = (int) ((Math.random() * 26 + 1) - 1);
            if (this.arr[num] > 0) {
                this.arr[num]--;
                return this.tiles[num];
            }
            return getRand();
        }

        public Tile getTile(char c) {
            if(c>='A'&& c<='Z') {
                if (size() == 0)
                    return null;
                int num = c - 'A';
                if (this.arr[num] > 0) {
                    this.arr[num]--;
                    return this.tiles[num];
                }
            }return null;
        }

        public void put(Tile t){
            char c = t.letter;
                int num = c - 'A';
                if (this.original[num] > this.arr[num])
                    this.arr[num]++;
        }

        public int size() {
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }
            return sum;
        }

        public int[] getQuantities() {
            return this.arr.clone();
        }

        public static Bag getBag() {
            if (item == null)
                item = new Bag();
            return item;
        }

    }
}
