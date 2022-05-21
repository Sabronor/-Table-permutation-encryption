package main;

class Permutation {
    int key;

    public Permutation(int key) {
        this.key = key;
    }

    public String encrypt(String message) {
        StringBuilder ret_string = new StringBuilder();

        for (int i = key - 1; i >= 0; i--) {
            for (char[] chars : encryptTable(message)) {
                ret_string.append(chars[i] == ' ' ? '_' : chars[i]);
            }
        }
        return ret_string.toString();
    }

    public String decrypt(String message) {
        StringBuilder ret_mes = new StringBuilder();

        for (char[] i : decryptTable(message)) {
            for (char j : i) {
                ret_mes.append(j);
            }
        }
        return ret_mes.toString();
    }

    private char[][] encryptTable(String message) {
        char[] mes = message.toCharArray();
        int rows = (int) Math.ceil((double) mes.length / (double) key);
        char[][] table = new char[rows][key];

        for (int i = 0; i < rows; i++) {
            int pointer = i > 0 ? key * i : 0;

            for (int j = 0; j < key; j++) {
                try {
                    table[i][j] = mes[pointer + j];
                } catch (IndexOutOfBoundsException e) {
                    table[i][j] = ' ';
                }
            }
        }
        return table;
    }

    private char[][] decryptTable(String message) {
        char[] mes = message.toCharArray();
        int rows = (int) Math.ceil((double) mes.length / (double) key);

        char[][] table = new char[rows][key];
        int pointer = 0;
        for (int i = key - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                table[j][i] = mes[pointer] == '_' ? ' ' : mes[pointer];
                pointer++;
            }
        }

        return table;
    }
}
