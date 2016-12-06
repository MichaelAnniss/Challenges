package me.mikey.challenges.week8;

/**
 * Created by Michael on 06/12/2016.
 */
public enum RoomDirection {
    NORTH {
        @Override
        public RoomDirection getOpposite() {
            return SOUTH;
        }
    },
    EAST {
        @Override
        public RoomDirection getOpposite() {
            return WEST;
        }
    },
    SOUTH {
        @Override
        public RoomDirection getOpposite() {
            return NORTH;
        }
    },
    WEST {
        @Override
        public RoomDirection getOpposite() {
            return EAST;
        }
    },
    NORTH_EAST {
        @Override
        public RoomDirection getOpposite() {
            return SOUTH_WEST;
        }
    },
    NORTH_WEST {
        @Override
        public RoomDirection getOpposite() {
            return SOUTH_EAST;
        }
    },
    SOUTH_EAST {
        @Override
        public RoomDirection getOpposite() {
            return NORTH_WEST;
        }
    },
    SOUTH_WEST {
        @Override
        public RoomDirection getOpposite() {
            return NORTH_EAST;
        }
    };

    public abstract RoomDirection getOpposite();
}
