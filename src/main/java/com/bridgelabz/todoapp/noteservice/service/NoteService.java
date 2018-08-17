package com.bridgelabz.todoapp.noteservice.service;

import java.io.IOException;
import java.security.acl.Group;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.todoapp.exceptionservice.ToDoException;
import com.bridgelabz.todoapp.noteservice.controller.NoteController;
import com.bridgelabz.todoapp.noteservice.dto.LabelDto;
import com.bridgelabz.todoapp.noteservice.dto.NoteDto;
import com.bridgelabz.todoapp.noteservice.model.Label;
import com.bridgelabz.todoapp.noteservice.model.MetaData;
import com.bridgelabz.todoapp.noteservice.model.Note;
import com.bridgelabz.todoapp.noteservice.repository.LabelElasticRepository;
import com.bridgelabz.todoapp.noteservice.repository.LabelRepository;
import com.bridgelabz.todoapp.noteservice.repository.NoteElasticRepository;
import com.bridgelabz.todoapp.noteservice.repository.NoteRepository;
import com.bridgelabz.todoapp.userservice.model.User;
import com.bridgelabz.todoapp.userservice.repository.UserRepository;
import com.bridgelabz.todoapp.utilservice.PreConditions;
import com.bridgelabz.todoapp.utilservice.Utility;
import com.bridgelabz.todoapp.utilservice.interceptor.TokenParseInterceptor;
import com.bridgelabz.todoapp.utilservice.messageAccessor.Messages;

import io.jsonwebtoken.Claims;

/**
 * @author bridgelabz
 * @since 19/07/2018 <br>
 *        <p>
 *        Business entity for noteService which has noteService implementation
 *        of functions performed on note. <br>
 *        </p>
 */
@Service
public class NoteService implements INoteService {
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	LabelRepository labelRepository;
	@Autowired
	Utility utility;
	@Autowired
	ModelMapper objectmapper;
	@Autowired
	TokenParseInterceptor tokenparseinterceptor;
	@Autowired
	Messages messages;
	@Autowired
	NoteElasticRepository noteElasticRepository;
	@Autowired
	LabelElasticRepository labelElasticRepository;
	// @Autowired
	// RedisRepository redisrepository;
	@Value("${urlpattern}")
	String urlpattern;

	public static final Logger logger = LoggerFactory.getLogger(NoteController.class);
	static String REQ_ID = "IN_Note";
	static String RESP_ID = "OUT_Note";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bridgelabz.todoapp.noteservice.service.INoteService#createNote(com.
	 * bridgelabz.todoapp.noteservice.model.Note, java.lang.String)
	 */
	@Override
	public String createNote(NoteDto notedto, String userId) throws ToDoException, IOException {
		logger.info("user note creating");
		PreConditions.checkNotNull(notedto.getTitle(), messages.get("10"));
		PreConditions.checkNotNull(notedto.getDescription(), messages.get("11"));
		PreConditions.checkNotNull(userId, messages.get("12"));
		logger.info(userId);

		Optional<User> user = userRepository.findById(userId);

		Note noteMap = objectmapper.map(notedto, Note.class);
		noteMap.setUserId(user.get().getId());

		MetaData metaData = new MetaData();
		List<MetaData> metaDataList = new ArrayList<>();
		String description = notedto.getDescription();
		logger.info(description);
		logger.info(urlpattern);
		Pattern pattern = Pattern.compile(urlpattern);
		Matcher matcher = pattern.matcher(description);
		matcher.matches();
		System.out.println(matcher.groupCount());
		// for (int i = 0; i < matcher.groupCount(); i++) {
		int i = 0;
		if (i < matcher.groupCount()) {
			String url = matcher.group(i);
			logger.info(url);
			metaData.setUrl(url);
			Document doc = Jsoup.connect(notedto.getDescription()).get();

			String title = doc.title();
			logger.info(title);
			metaData.setTitle(title);

			Element image = doc.select("img").first();
			String imageUrl = image.absUrl("src");
			logger.info(imageUrl);
			metaData.setImageUrl(imageUrl);

			metaDataList.add(metaData);
			System.out.println(metaDataList);
		}
		noteMap.setMetaDataList(metaDataList);
		System.out.println(noteMap);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String createdAt = simpleDateFormat.format(new Date());
		noteMap.setCreatedAt(createdAt);
		noteMap.setUpdatedAt(createdAt);
		noteRepository.save(noteMap);
		noteElasticRepository.save(noteMap);
		logger.info("user note created");
		return noteMap.getNoteId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#deleteNote(java.lang.
	 * String, java.lang.String)
	 */

	@Override
	public void deleteNote(String noteId, String userId) throws ToDoException {
		logger.info("user note deleting");
		PreConditions.checkNotNull(noteId, messages.get("13"));
		if (!noteElasticRepository.existsById(noteId)) {

			throw new ToDoException(messages.get("23"));
		}
		noteRepository.deleteById(noteId);
		noteElasticRepository.deleteById(noteId);
		logger.info("user note deleted");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#updateNote(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateNote(String noteId, String title, String description, String userId) throws ToDoException {
		logger.info("user note updating");
		PreConditions.checkNotNull(title, messages.get("10"));
		PreConditions.checkNotNull(description, messages.get("11"));

		if (!noteElasticRepository.existsById(noteId)) {
			throw new ToDoException(messages.get("23"));
		}
		Optional<Note> note1 = noteElasticRepository.getByNoteId(noteId);

		Note note = new Note();
		note.setNoteId(noteId);
		note.setTitle(title);
		note.setNoteId(note1.get().getUserId());
		note.setCreatedAt(note1.get().getCreatedAt());
		note.setDescription(description);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		note.setUpdatedAt(simpleDateFormat.format(new Date()));
		noteRepository.save(note);
		noteElasticRepository.save(note);
		logger.info("user note updated");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#displayAllNotes(java.
	 * lang.String)
	 */
	@Override
	public List<Note> displayAllNotes(String userId) throws ToDoException {
		List<Note> list = null;
		PreConditions.checkNotNull(userId, messages.get("12"));
		if (!userRepository.findById(userId).isPresent()) {
			throw new ToDoException(messages.get("24"));
		}
		Optional<User> user = userRepository.findById(userId);
		List<Note> newNoteList = new ArrayList<Note>();
		list = noteRepository.findAll();
		List<Note>list1= noteElasticRepository.findNotesByUserId(user.get().getId());
		list1.stream().filter(noteStream -> (noteStream.isPinnedStatus() == true && noteStream.isTrashStatus() == false
				&& noteStream.isArchivestatus() == false)).forEach(noteFilter -> newNoteList.add(noteFilter));
		list1.stream().filter(noteStream -> (noteStream.isPinnedStatus() == false && noteStream.isTrashStatus() == false
				&& noteStream.isArchivestatus() == false)).forEach(noteFilter -> newNoteList.add(noteFilter));
		list1.stream().filter(noteStream -> (noteStream.isPinnedStatus() == false && noteStream.isTrashStatus() == false
				&& noteStream.isArchivestatus() == true)).forEach(noteFilter -> newNoteList.add(noteFilter));
	   // list=(List<Note>) noteElasticRepository.findAll();
		System.out.println(user.get().getId());
		return newNoteList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#deleteFromTrash(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void deleteFromTrash(String noteId, String token) throws ToDoException {
		logger.info(" Deleting a Note from the trash");
		PreConditions.checkNotNull(noteId, messages.get("13"));
		PreConditions.checkNotNull(token, messages.get("12"));

		logger.info(noteId);
		if (noteElasticRepository.existsById(noteId)) {
			throw new ToDoException(messages.get("25"));
		}
		noteRepository.deleteById(noteId);
		noteElasticRepository.deleteById(noteId);
		logger.info(" Note Deleted Permanently ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#restoreFromTrash(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public void restoreFromTrash(String noteId, String token) throws ToDoException {
		logger.info(" Restoring from trash");
		PreConditions.checkNotNull(noteId, messages.get("13"));
		PreConditions.checkNotNull(token, messages.get("12"));

		if (!noteElasticRepository.existsById(noteId)) {
			throw new ToDoException(messages.get("25"));
		}
		Note note = noteElasticRepository.getByNoteId(noteId).get();
		if (note.isTrashStatus()) {
			note.setTrashStatus(false);
		}
		logger.info("restored from trash");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#pinnote(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public void pinnote(String noteId, String token) throws ToDoException {
		logger.info(" pinning a note");
		PreConditions.checkNotNull(noteId, messages.get("13"));
		PreConditions.checkNotNull(token, messages.get("12"));

		if (!noteElasticRepository.existsById(noteId)) {
			throw new ToDoException(messages.get("25"));
		}
		Note note = noteElasticRepository.getByNoteId(noteId).get();
		if (note.isTrashStatus()) {
			note.setPinnedStatus(true);
			noteRepository.save(note);
			noteElasticRepository.save(note);
		}
		logger.info(" note is pinned");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#archivenote(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void archivenote(String noteId, String token) throws ToDoException {
		logger.info("note is archiving");
		PreConditions.checkNotNull(noteId, messages.get("13"));
		PreConditions.checkNotNull(token, messages.get("12"));
		Note note = noteElasticRepository.getByNoteId(noteId).get();
		if (note.isTrashStatus()) {
			logger.info(messages.get("26"));
		}
		if (note.isArchivestatus())
			logger.info(messages.get("27"));
		note.setArchivestatus(true);
		noteRepository.save(note);
		noteElasticRepository.save(note);
		logger.info(" note archived");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#setReminder(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	@Override
	public Note setReminder(String noteId, String reminderTime, String userId) throws ParseException, ToDoException {
		logger.info("reminder started");
		Optional<Note> note = PreConditions.checkNotNull(noteRepository.findById(noteId), "No notes found");

		Timer timer = null;
		if (note.isPresent()) {
			Date reminder = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(reminderTime);
			long timeDifference = reminder.getTime() - new Date().getTime();
			timer = new Timer();
			Optional<User> user = userRepository.findById(userId);
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					logger.info("Reminder task:" + note.toString());
				}
			}, timeDifference);
		}
		return note.get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bridgelabz.todoapp.noteservice.service.INoteService#createlabel(com.
	 * bridgelabz.todoapp.noteservice.dto.LabelDto, java.lang.String)
	 */
	@Override
	public MetaData getMetaData(NoteDto notedto, String userId) throws ToDoException, IOException {
		logger.info("getting here!!!!!!!");
		PreConditions.checkNotNull(userId, messages.get("12"));
		Note noteMap = objectmapper.map(notedto, Note.class);
		MetaData metaData = new MetaData();
		List<MetaData> metaDataList = new ArrayList<>();
		String description = notedto.getDescription();
		logger.info(description);
		logger.info(urlpattern);
		Pattern pattern = Pattern.compile(urlpattern);
		Matcher matcher = pattern.matcher(description);
		matcher.matches();
		System.out.println(matcher.groupCount());
		// for (int i = 0; i < matcher.groupCount(); i++) {
		int i = 0;
		if (i < matcher.groupCount()) {
			String url = matcher.group(i);
			logger.info(url);
			metaData.setUrl(url);
			Document doc = Jsoup.connect(notedto.getDescription()).get();

			String title = doc.title();
			logger.info(title);
			metaData.setTitle(title);

			Element image = doc.select("img").first();
			String imageUrl = image.absUrl("src");
			logger.info(imageUrl);
			metaData.setImageUrl(imageUrl);

			metaDataList.add(metaData);
			System.out.println(metaDataList);
		}
		noteMap.setMetaDataList(metaDataList);
		System.out.println(noteMap);
		noteRepository.save(noteMap);
		// noteElasticRepository.save(noteMap);
		return metaData;
	}

	@Override
	public String createlabel(LabelDto labelDto, String userId) throws ToDoException {
		logger.info(" label creating");
		PreConditions.checkNotNull(labelDto.getLabelId(), messages.get("14"));
		PreConditions.checkNotNull(labelDto.getUserId(), messages.get("4"));
		Optional<User> user = userRepository.findById(userId);

		Label labelMap = objectmapper.map(labelDto, Label.class);

		List<Label> list = labelRepository.findAll();
		for (Label l : list) {
			if (l.getLabelId().equals(labelMap.getLabelId()))
				throw new ToDoException(messages.get("29"));
		}
		labelRepository.save(labelMap);
		labelElasticRepository.save(labelMap);
		logger.info(" label created");
		return labelMap.getLabelId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#deletelabel(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void deletelabel(String labelId, String userId) throws ToDoException {
		logger.info(" label deleting");
		PreConditions.checkNotNull(labelId, messages.get("14"));
		if (!labelRepository.existsById(labelId)) {
			throw new ToDoException(messages.get("30"));
		}
		// Claims email = utility.parseJwt(token);
		labelRepository.deleteById(labelId);
		labelElasticRepository.deleteById(labelId);
		logger.info(" label deleted");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#updatelabel(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updatelabel(String labelId, String labelName, String userId) throws ToDoException {
		logger.info(" label updating");
		PreConditions.checkNotNull(labelId, messages.get("14"));
		PreConditions.checkNotNull(labelName, messages.get("15"));
		if (!labelRepository.existsById(labelId)) {
			throw new ToDoException(messages.get("30"));
		}
		Label label = labelRepository.findById(labelId).get();
		label.setLabelName(labelName);
		labelRepository.save(label);
		labelElasticRepository.save(label);
		logger.info(" label updated");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#addlabel(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addlabel(String noteId, String labelname, String token) throws ToDoException {
		Claims email = utility.parseJwt(token);
		Optional<User> user = userRepository.findByEmail(email.getId());
		if (user == null)
			throw new ToDoException(messages.get("31"));
		List<Note> list = noteRepository.findAll();

		for (Note n : list) {
			if (n.getNoteId().equals(noteId)) {
				Optional<Note> optionalnote = noteRepository.findById(noteId);
				if (!optionalnote.isPresent()) {
					throw new ToDoException(messages.get("28"));
				}
				Note note = optionalnote.get();
				note.setLabelname(labelname);
				noteRepository.save(note);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.noteservice.service.INoteService#displayAllLabel(java.
	 * lang.String)
	 */
	@Override
	public List<Label> displayAllLabel(String userId) throws ToDoException {
		List<Label> list = null;
		// Claims email = utility.parseJwt(token);
		if (!userRepository.findById(userId).isPresent()) {
			throw new ToDoException(messages.get("24"));
		}
		Optional<User> user = userRepository.findById(userId);
		list = labelRepository.findAll();
		return list;
	}
}
