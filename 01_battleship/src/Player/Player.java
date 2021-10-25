package Player;

import Models.CellState;
import Models.Turn;
import Models.TurnResult;

import javax.swing.*;
import java.awt.Color;
import java.util.LinkedList;

public class Player {
    private static final int BG_SIZE = 10;
    private static final String[] X_AXIS_LABELS = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static final String[] Y_AXIS_LABELS = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    private CellState[][] bgYou = new CellState[BG_SIZE][BG_SIZE];
    private LinkedList<LinkedList<Turn>> shipsYou = new LinkedList<>();

    private JButton[][] btnYou;
    private JButton[][] btnOpp;

    private JFrame frame;

    private JComboBox<String> boxShips;
    private DefaultComboBoxModel<String> modelShips;
    private JComboBox<String> boxOrient;
    private DefaultComboBoxModel<String> modelOrient;
    private JLabel labelHint;
    private int[] shipLimit = new int[]{0, 4, 3, 2, 1};

    private JButton btnConnect;
    private JLabel labelStatus;
    private JLabel labelGame;
    private JList<String> log;
    private DefaultListModel<String> model_log;
    private JTextField port;

    private PlayerService controller;
    private boolean canChange = true;
    private boolean canMakeTurn = false;
    private Turn lastTurn;

    public Player(JFrame frame_) {
        frame = frame_;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 650);
        frame.setLayout(null);

        JLabel labelYou = new JLabel(Strings.YOUR_BG);
        labelYou.setBounds(20, 0, 400, 50);
        labelYou.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(labelYou);

        btnYou = drawBattleground(frame, 0, 50, true);

        for (int i = 0; i < BG_SIZE; i++) {
            for (int j = 0; j < BG_SIZE; j++) {
                bgYou[i][j] = CellState.SEA;
            }
        }

        modelShips = new DefaultComboBoxModel<>();
        modelShips.addElement("1-deck ship");
        modelShips.addElement("2-deck ship");
        modelShips.addElement("3-deck ship");
        modelShips.addElement("4-deck ship");

        boxShips = new JComboBox<>();
        boxShips.setBounds(10, 520, 200, 30);
        boxShips.setModel(modelShips);
        frame.add(boxShips);

        modelOrient = new DefaultComboBoxModel<>();
        modelOrient.addElement("Horizontal");
        modelOrient.addElement("Vertical");

        boxOrient = new JComboBox<>();
        boxOrient.setBounds(220, 520, 200, 30);
        boxOrient.setModel(modelOrient);
        frame.add(boxOrient);

        labelHint = new JLabel("Choose ship type and then click to the battleground's cell to place it");
        labelHint.setBounds(10, 550, 400, 30);
        frame.add(labelHint);

        frame.setVisible(true);
    }

    private void switchToGame() {
        labelHint.setVisible(false);
        labelHint.setEnabled(false);
        boxShips.setVisible(false);
        boxShips.setEnabled(false);
        boxOrient.setVisible(false);
        boxOrient.setEnabled(false);

        frame.setSize(1000, 700);

        btnOpp = drawBattleground(frame, 460, 50, false);

        btnConnect = new JButton(Strings.CONNECT);
        btnConnect.setBounds(110, 520, 100, 30);
        btnConnect.addActionListener(e -> onConnectButtonClicked());
        frame.add(btnConnect);

        port = new JTextField();
        port.setBounds(10, 520, 100, 30);
        port.setText("5924");
        frame.add(port);

        labelStatus = new JLabel("Press \"Connect\" to start");
        labelStatus.setBounds(10, 550, 400, 30);
        frame.add(labelStatus);

        labelGame = new JLabel("");
        labelGame.setBounds(10, 580, 350, 30);
        frame.add(labelGame);

        log = new JList<>();
        log.setBounds(450, 520, 400, 100);
        model_log = new DefaultListModel<>();
        log.setModel(model_log);
        frame.add(log);

        JLabel labelOpp = new JLabel(Strings.OPP_BG);
        labelOpp.setBounds(500, 0, 400, 50);
        labelOpp.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(labelOpp);
    }

    private void setButtonColor(JButton button, Color color) {
        button.setBackground(color);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
    }

    private void setStatus(String status) {
        labelStatus.setText(status);
    }

    private void pushLog(String message) {
        if (model_log.size() >= 5)
            model_log.remove(0);
        model_log.addElement(message);
    }

    public void onConnectButtonClicked() {
        if (controller != null && controller.isConnected()) {
            controller.disconnect();
            return;
        }
        btnConnect.setText(Strings.CANCEL);
        setStatus("Connecting...");
        pushLog("Connection process initiated");
        controller = new PlayerService(this, Integer.parseInt(port.getText()));
        controller.start();
    }

    private void updateGameLabel() {
        if (canMakeTurn) {
            labelGame.setText(Strings.YOUR_TURN);
        } else {
            labelGame.setText(Strings.OPP_TURN);
        }
    }

    public void onConnectionEstablished() {
        btnConnect.setText(Strings.DISCONNECT);
        setStatus(Strings.CONNECTED);
        pushLog("Connection established successfully");
        canChange = false;
        canMakeTurn = controller.isHost();
        updateGameLabel();
    }

    public void onWaitingOtherPlayer() {
        setStatus("Waiting for other player to connect...");
        pushLog("Waiting for other player to connect started");
    }

    public void onDisconnection() {
        btnConnect.setText(Strings.CONNECT);
        setStatus(Strings.DISCONNECTED);
        pushLog(Strings.DISCONNECTED);
    }

    private static int clamp(int value, int min, int max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    private boolean checkAvailableCell(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (bgYou[clamp(x + i, 0, BG_SIZE - 1)][clamp(y + j, 0, BG_SIZE - 1)] != CellState.SEA) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placeShip(int x, int y) {
        int shipType = Integer.parseInt(((String)modelShips.getSelectedItem()).substring(0, 1));
        if (shipLimit[shipType] == 0) {
            labelHint.setText("No ships of selected type is available anymore");
            return;
        }
        boolean isHorizontal = boxOrient.getSelectedIndex() == 0;
        int dx = isHorizontal ? 1 : 0;
        int dy = isHorizontal ? 0 : 1;
        if (x + dx * (shipType - 1) >= BG_SIZE || y + dy * (shipType - 1) >= BG_SIZE) {
            labelHint.setText("Not enough place for selected ship");
            return;
        }
        for (int c = 0, i = x, j = y; c < shipType; c++, i += dx, j += dy) {
            if (!checkAvailableCell(i, j)) {
                labelHint.setText("There are conflicting cells on selected ship placement");
                return;
            }
        }
        shipsYou.addLast(new LinkedList<Turn>());
        LinkedList<Turn> cells = shipsYou.getLast();
        for (int c = 0, i = x, j = y; c < shipType; c++, i += dx, j += dy) {
            setButtonColor(btnYou[i][j], Color.BLUE);
            bgYou[i][j] = CellState.SHIP;
            cells.add(new Turn(i, j));
        }
        shipLimit[shipType]--;
        labelHint.setText("Ship was placed successfully");
        if (shipLimit[shipType] == 0) {
            modelShips.removeElementAt(boxShips.getSelectedIndex());
        }
        if (modelShips.getSize() == 0)
            switchToGame();
    }

    private void onButtonClicked(int x, int y, boolean isYou) {
        if (isYou && canChange) {
            placeShip(x, y);
        } else if (!isYou && canMakeTurn) {
            canMakeTurn = false;
            setButtonColor(btnOpp[x][y], Color.YELLOW);
            lastTurn = new Turn(x, y);
            controller.sendTurn(lastTurn);
        }
    }

    private JButton[][] drawBattleground(JFrame frame, int x, int y, boolean isYou) {
        for (int i = 0; i < BG_SIZE; i++) {
            JLabel label = new JLabel(Y_AXIS_LABELS[i]);
            label.setBounds(x, y + (i + 1) * 40, 38, 38);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            frame.add(label);
        }
        for (int i = 0; i < BG_SIZE; i++) {
            JLabel label = new JLabel(X_AXIS_LABELS[i]);
            label.setBounds(x + (i + 1) * 40, y, 38, 38);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            frame.add(label);
        }
        JButton[][] buttons = new JButton[BG_SIZE][BG_SIZE];
        for (int i = 0; i < BG_SIZE; i++) {
            for (int j = 0; j < BG_SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBounds(x + (i + 1) * 40, y + (j + 1) * 40, 38, 38);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(e -> onButtonClicked(finalI, finalJ, isYou));
                setButtonColor(buttons[i][j], Color.LIGHT_GRAY);
                frame.add(buttons[i][j]);
            }
        }
        return buttons;
    }

    public TurnResult processOppTurn(Turn turn) {
        pushLog("Opponent's turn is %s,%s".formatted(X_AXIS_LABELS[turn.x], Y_AXIS_LABELS[turn.y]));
        TurnResult turnResult = TurnResult.MISSED;
        int idx = 0;
        int shipToRemove = -1;
        for (LinkedList<Turn> cells : shipsYou) {
            Turn cellToRemove = null;
            for (Turn cell : cells) {
                if (cell.x == turn.x && cell.y == turn.y)
                    cellToRemove = cell;
            }
            if (cellToRemove != null) {
                cells.remove(cellToRemove);
                turnResult = TurnResult.WOUNDED;
                if (cells.size() == 0) {
                    turnResult = TurnResult.KILLED;
                    shipToRemove = idx;
                }
            }
            idx++;
        }
        if (shipToRemove != -1) {
            shipsYou.remove(shipToRemove);
            if (shipsYou.size() == 0) {
                turnResult = TurnResult.KILLED_AND_WON;
            }
        }
        bgYou[turn.x][turn.y] = turnResult == TurnResult.MISSED ? CellState.SEA_HIT : CellState.SHIP_HIT;
        if (turnResult == TurnResult.MISSED) {
            setButtonColor(btnYou[turn.x][turn.y], Color.WHITE);
        } else {
            setButtonColor(btnYou[turn.x][turn.y], Color.RED);
        }
        canMakeTurn = turnResult == TurnResult.MISSED;
        updateGameLabel();
        if (turnResult == TurnResult.KILLED_AND_WON) {
            labelGame.setText("You lose - game over");
            canMakeTurn = false;
        }
        return turnResult;
    }

    private static Color colorByTurnResult(TurnResult turnResult) {
        return switch (turnResult) {
            case MISSED -> Color.WHITE;
            case WOUNDED, KILLED_AND_WON, KILLED -> Color.RED;
        };
    }

    public void processOppTurnResult(TurnResult turnResult) {
        pushLog("Opponent sent " + turnResult + " on your last turn");
        setButtonColor(btnOpp[lastTurn.x][lastTurn.y], colorByTurnResult(turnResult));
        canMakeTurn = turnResult == TurnResult.WOUNDED || turnResult == TurnResult.KILLED;
        updateGameLabel();
        if (turnResult == TurnResult.KILLED_AND_WON) {
            labelGame.setText("You win - game over");
            canMakeTurn = false;
        }
    }

    public static void main(String[] args) {
        new Player(new JFrame(Strings.BATTLESHIP));
    }
}
