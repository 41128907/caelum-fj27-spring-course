package br.com.alura.forum.domain.topic;

import br.com.alura.forum.domain.Answer;

public interface TopicState {

    void registerNewReply(Topic topic, Answer newReply);
	void markAsSolved(Topic topic);
	void close(Topic topic);
}
