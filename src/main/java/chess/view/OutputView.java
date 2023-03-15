package chess.view;

import chess.dto.BoardDto;

import java.util.List;

public class OutputView {

    private static final String STATE_COMMAND_GUIDE_MESSAGE = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final String START_GUIDE_MESSAGE = "체스 게임을 시작합니다.";

    public static void printStartGuideMessage(){
        System.out.println(START_GUIDE_MESSAGE);
    }

    public static void printCommandGuideMessage() {

        System.out.println(STATE_COMMAND_GUIDE_MESSAGE);
    }
    public static void printBoard(BoardDto boardDto) {
        List<List<String>> board = boardDto.getDto();

        for(List<String> line : board) {
            line.stream()
                    .forEach(square -> System.out.print(square));
            System.out.println();
        }

    }
}
