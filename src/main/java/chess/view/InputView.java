package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String STATE_COMMAND_GUIDE_MESSAGE = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final Scanner scanner = new Scanner(System.in);

    public static String readStateCommand(){

        return scanner.nextLine();
    }

    public static List<String> readMoveCommand() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(" "))
                .map(each -> each.strip())
                .collect(Collectors.toList());
    }
}
