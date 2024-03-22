package fr.eni.efay.bo;

import java.io.Serializable;
import java.util.Objects;

public class Feedback implements Serializable {
    private long feedback_id;
    private String comment;
    private int note;
    private User buyer_id;
    private User seller_id;

    public Feedback() {
    }

    public Feedback(String comment, int note, User buyer_id, User seller_id) {
        this.comment = comment;
        this.note = note;
        this.buyer_id = buyer_id;
        this.seller_id = seller_id;
    }

    public Feedback(long feedback_id, String comment, int note, User buyer_id, User seller_id) {
        this.feedback_id = feedback_id;
        this.comment = comment;
        this.note = note;
        this.buyer_id = buyer_id;
        this.seller_id = seller_id;
    }

    public long getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(long feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public User getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(User buyer_id) {
        this.buyer_id = buyer_id;
    }

    public User getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(User seller_id) {
        this.seller_id = seller_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return feedback_id == feedback.feedback_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedback_id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Feedback{");
        sb.append("feedback_id=").append(feedback_id);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", note=").append(note);
        sb.append(", buyer_id=").append(buyer_id);
        sb.append(", seller_id=").append(seller_id);
        sb.append('}');
        return sb.toString();
    }
}
