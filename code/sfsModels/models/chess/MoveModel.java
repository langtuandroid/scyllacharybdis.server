package models.chess;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class MoveModel implements SerializableSFSType
{
    public static final byte MOVE = 0;
    public static final byte CAPTURE = 1;
    public static final byte PAWN_MOVE_TWO = 1 << 1;
    public static final byte CASTLE = 1 << 2;
    public static final byte EN_PASSANT = 1 << 3;
    
	String from;
	String to;
	byte type;
	Boolean valid;

	public MoveModel()
	{
	}

	public MoveModel(String from, String to, byte type, Boolean valid)
	{
		this.setFrom(from);
		this.setTo(to);
		this.setType(type);
		this.setValid(valid);
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTo() {
		return to;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getType() {
		return type;
	}
	
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Boolean getValid() {
		return valid;
	}
}