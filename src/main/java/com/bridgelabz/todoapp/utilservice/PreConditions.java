package com.bridgelabz.todoapp.utilservice;

import org.springframework.lang.Nullable;

import com.bridgelabz.todoapp.exceptionservice.ToDoException;
import com.bridgelabz.todoapp.noteservice.dto.NoteDto;

/**
 * @author bridgelabz
 * @since 20/07/2018 <br>
 *        <p>
 *        Entity to check conditions or to validate the fields it has. <br>
 *        </p>
 */
public class PreConditions {

	public PreConditions() {
	}

	/**
	 * @param reference
	 * @param errorMessage
	 *            <p>
	 *            Function is to check for null object and returns object if not
	 *            null
	 *            </p>
	 * @return reference
	 * @throws ToDoException
	 */
	public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) throws ToDoException {
		if (reference == null) {
			throw new ToDoException(String.valueOf(errorMessage));
		}
		return reference;
	}
	/**
	 * @param reference
	 * @return
	 * @throws ToDoException
	 */
	public static boolean isPresentInDB(boolean reference) throws ToDoException {
		if (!reference) {
			throw new ToDoException(String.valueOf(""));
		}
		return reference;
	}

	/**
	 * @param reference
	 * @param errorMessage
	 * @return
	 * @throws ToDoException
	 */
	public static <T> String checkNotNull(String reference, @Nullable Object errorMessage) throws ToDoException {
		if (reference.equals("")) {
			throw new ToDoException(String.valueOf(errorMessage));
		}
		return reference;
	}

	/**
	 * @param errorMessage
	 * @throws ToDoException
	 */
	public static <T> void checkString(@Nullable Object errorMessage) throws ToDoException {
		throw new ToDoException(String.valueOf(errorMessage));
	}
}