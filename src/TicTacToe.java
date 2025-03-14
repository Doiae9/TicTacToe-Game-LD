import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe {
    int boardWidth =600;
    int boardHeigth =650; //50px para el panel

    //Aqui colocamos la interfaz que se va a utilizar
    JFrame frame = new JFrame("TicTacToe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];

    JButton restartButton = new JButton("Restart");

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    String gameTitle = "TicTacToe";

    int turns =0;

    boolean gameOver = false;

    //Opciones que vamos a usar para la interfaz
    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeigth);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText(gameTitle);
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);


        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);

//    if(gameOver) {
//
        //New panel
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
//    }




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
    }
    void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if(board[r][0].getText().isEmpty()) continue;
            if(board[r][0].getText() == board[r][1].getText() &&
                    board[r][1].getText() == board[r][2].getText()){
                for(int i = 0; i < 3; i++){
                    setWinner(board[r][i]);
                }
                    gameOver = true;
                    restartButton.setVisible(true);
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
                gameOver = true;
                restartButton.setVisible(true);
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
            gameOver = true;
            restartButton.setVisible(true);
            return;
        }
        //Diagonal-contraria
        if(board[2][0].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[0][2].getText() &&
                board[0][2].getText() != ""){

            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            restartButton.setVisible(true);
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


}

