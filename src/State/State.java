package State;

public enum State {
	
	GAME_MENU, PLAYING, OPTION_MENU;
	
	private static State currentState;
	
	public static void setState(State state) {
		State.currentState = state;
	}
	
	public static boolean isState(State state) {
		return State.currentState == state;
	}
	
	public static State getState() {
		return State.currentState;
	}

}
