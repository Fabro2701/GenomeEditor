package genome_editing.model;

import java.util.HashMap;



public final class Constants {
	
	public static final int CHROMOSOME_LENGTH = 70;
	public static final int PLOIDY = 3;
	
	
	public enum NODE_TYPE {
		LAND, VOID;
	}

	public enum ACTION {
		NOTHING, REPRODUCTION, ATTACK;
	}
	public enum STATE {
		EAT, MOVE, REST;
	}
	public enum MOVE { 
		
		UP(new Pair<>(0, -1),false) {
		},
		RIGHT(new Pair<>(1, 0),false) {
		},
		DOWN(new Pair<>(0, 1),false) {
		},
		LEFT(new Pair<>(-1, 0),false) {
		},
		NEUTRAL(new Pair<>(0, 0),false) {
		},
		//pseudo moves
		CHASE_UP(new Pair<>(0, -1),true) {
		},
		CHASE_RIGHT(new Pair<>(1, 0),true) {
		},
		CHASE_DOWN(new Pair<>(0, 1),true) {
		},
		CHASE_LEFT(new Pair<>(-1, 0),true) {
		},
		RUNAWAY_UP(new Pair<>(0, -1),true) {
		},
		RUNAWAY_RIGHT(new Pair<>(1, 0),true) {
		},
		RUNAWAY_DOWN(new Pair<>(0, 1),true) {
		},
		RUNAWAY_LEFT(new Pair<>(-1, 0),true) {
		};
		Pair<Integer, Integer>change;
		boolean pseudo;
		private MOVE(Pair<Integer, Integer>change, boolean pseudo) {
			this.change = change;
			this.pseudo = pseudo;
		}
		public Pair<Integer, Integer> getPosChange(){
			return change;
		}
		public boolean isPseudo() {
			return pseudo;
		}
	}
	public static float cosf(float x) {
		return (float) Math.cos(x);
	}
	public static float sinf(float x) {
		return (float) Math.sin(x);
	}
	public static float tanf(float x) {
		return (float) Math.tan(x);
	}
}
