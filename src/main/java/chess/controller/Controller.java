package chess.controller;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.boardStrategy.InitialBoardStrategy;
import chess.domain.game.ChessGame;
import chess.dto.BoardDto;

import java.util.List;

import static chess.view.InputView.readMoveCommand;
import static chess.view.InputView.readStateCommand;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printCommandGuideMessage;

public class Controller {

    private boolean flag = false;

    public void generate() {
        printCommandGuideMessage();
        ChessGame chessGame = new ChessGame(new InitialBoardStrategy());
        judgeState();
        while (flag) {
            BoardDto boardDto = new BoardDto(chessGame.getChessBoard());
            printBoard(boardDto);
            playChess(chessGame);
        }

    }

    private void judgeState() {
        String command = readStateCommand();
        if (command.equals("start")) {
            flag = true;
        }
        if (command.equals("end")) {
            flag = false;
        }
        if(command.startsWith("move")){

        }
    }

    private void playChess(ChessGame chessGame) {
        try {
            List<String> moveCommand = readMoveCommand();
            if (moveCommand.get(0).equals("move")) {

                List<String> startCommand = List.of(moveCommand.get(1).split(""));
                Position start = new Position(Column.findColumnByValue(startCommand.get(0)), Rank.findRankByValue(startCommand.get(1)));

                List<String> endCommand = List.of(moveCommand.get(2).split(""));
                Position end = new Position(Column.findColumnByValue(endCommand.get(0)), Rank.findRankByValue(endCommand.get(1)));

                chessGame.move(start, end);

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void playChessGame() {
        ChessGame chessGame = new ChessGame(new InitialBoardStrategy());
        BoardDto boardDto = new BoardDto(chessGame.getChessBoard());
        printBoard(boardDto);

    }

}
