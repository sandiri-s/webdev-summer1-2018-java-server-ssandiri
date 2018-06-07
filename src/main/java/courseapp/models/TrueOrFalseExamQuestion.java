package courseapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TrueOrFalseExamQuestion extends BaseExamQuestion {
	@Column(name = "IS_TRUE", nullable = false)
	private boolean isTrue;
	public boolean getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
}