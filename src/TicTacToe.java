import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe {
    int boardWidth =600;
    int boardHeight =650; //50px para el panel

    //Aqui colocamos la interfaz que se va a utilizar
    JFrame frame = new JFrame("TicTacToe");
    JLabel textLabel = new JLabel();
    JPanel titlePanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JLabel scoreTextLabel = new JLabel();


    JButton[][] board = new JButton[3][3];

    JButton restartButton = new JButton("Restart");

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    String gameTitle = "TicTacToe";

    int turns =0;

    int ScoreX = 0;
    int ScoreO = 0;

    int partida=0;

    boolean gameOver = false;

    //Opciones que vamos a usar para la interfaz
    TicTacToe(){
        //Configuración del frame
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //configuración del título
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText(gameTitle);
        textLabel.setOpaque(true);

        //puntuacion
        scoreTextLabel.setBackground(Color.darkGray);
        scoreTextLabel.setForeground(Color.white);
        scoreTextLabel.setFont(new Font("Arial", Font.BOLD, 50));
        scoreTextLabel.setFocusable(false);
        scoreTextLabel.setText(ScoreX + "-" + ScoreO);
        scoreTextLabel.setHorizontalAlignment(JLabel.CENTER);

        //TopPanel
        JPanel topPanel= new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        //agregados
        topPanel.setBackground(Color.darkGray);
        topPanel.add(textLabel);
        topPanel.add(scoreTextLabel);

// Agrega el topPanel a la región NORTH
        frame.add(topPanel, BorderLayout.NORTH);

        //titlePanel
        titlePanel.add(textLabel);
        topPanel.add(titlePanel);
        titlePanel.setBackground(Color.darkGray);

        //Score panel
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(Color.darkGray);
        scorePanel.setForeground(Color.white);
        //scorePanel.setFocusable(false);
        topPanel.add(scorePanel);
        frame.add(topPanel, BorderLayout.NORTH);

        //Panel del tablero
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel, BorderLayout.CENTER);
//    if(gameOver) {
//
        //New panel-restartPanel
         JPanel controlPanel = new JPanel();
         controlPanel.setBackground(Color.darkGray);
         controlPanel.add(restartButton);
         frame.add(controlPanel, BorderLayout.SOUTH);

        //Restart button creation
         restartButton.setBackground(Color.gray);
         restartButton.setForeground(Color.white);
         restartButton.setFont(new Font("Arial", Font.BOLD, 50));
         restartButton.setFocusable(false);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });

        restartButton.setVisible(false);

        frame.add(topPanel, BorderLayout.NORTH);

        //Colocar el tablero
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton title = new JButton();
                board[r][c] = title;
                boardPanel.add(title);

                title.setBackground(Color.darkGray);
                title.setForeground(Color.white);
                title.setFont(new Font("Arial", Font.BOLD,120));
                title.setFocusable(false);
                // title.setText(cirremtPlayer);

                title.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;

                        JButton title =(JButton) e.getSource();
                        if(title.getText().isEmpty()){
                            title.setText(currentPlayer);
                            turns++;
                            checkWinner();

                            if(!gameOver){
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText("Turno de: " + currentPlayer);
                            }
                        }

                    }
                });
            }
        }
    //fame visible is here for take load components
    frame.setVisible(true);    }

    void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if(board[r][0].getText().isEmpty()) continue;
            if(board[r][0].getText() == board[r][1].getText() &&
                    board[r][1].getText() == board[r][2].getText()){
                for(int i = 0; i < 3; i++){
                    setWinner(board[r][i]);
                }
                    gameOver();
                    return;
            }
        }
         //vertical
        for (int c = 0; c < 3; c++) {
            if(board[0][c].getText().isEmpty()) continue;
            if(board[0][c].getText() == board[1][c].getText() &&
                    board[1][c].getText() == board[2][c].getText()){
                for(int j = 0; j < 3; j++){
                    setWinner(board[j][c]);
                }
                gameOver();
                return;
            }
        }
        //diagonal
        if(board[0][0].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][2].getText() &&
           board[0][0].getText() != ""){
            for(int k = 0; k < 3; k++){
                setWinner(board[k][k]);
            }
            gameOver();
            return;
        }
        //Diagonal-contraria
        if(board[2][0].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[0][2].getText() &&
                board[0][2].getText() != ""){

            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver();
            return;
        }

        if (turns == 9){
            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 3; c++){
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
            restartButton.setVisible(true);
        }


    }
    void setWinner(JButton title) {
        textLabel.setText("Ganaste "+ currentPlayer);
        title.setBackground(Color.green);
        title.setForeground(Color.gray);

    }

    void setTie(JButton title) {
        title.setForeground(Color.orange);
        title.setBackground(Color.gray);
        textLabel.setText("Empate!");
    }

    void restart() {

        gameOver = false;
        turns = 0;
        currentPlayer = playerX;
        textLabel.setText(gameTitle);

        for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            board[r][c].setText("");
            board[r][c].setBackground(Color.darkGray);
            board[r][c].setForeground(Color.white);
        }
    }
        restartButton.setVisible(false);
    }

    void gameOver() {
        gameOver = true;
        restartButton.setVisible(true);
        partida ++;
        if(currentPlayer== playerO) ScoreO++;
        if(currentPlayer== playerX) ScoreX++;
        scoreTextLabel.setText(ScoreX + "-" + ScoreO);
    }


}

