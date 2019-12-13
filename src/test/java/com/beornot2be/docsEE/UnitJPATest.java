package com.beornot2be.docsEE;

import com.beornot2be.docsEE.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitJPATest {
	private static BCryptPasswordEncoder passwordEncoder  =  new BCryptPasswordEncoder();;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private DocumentFileRepository documentFileRepository;

	@Autowired
	private FileTypeRepository fileTypeRepository;

	@Autowired
	private DocumentPermissionRepository documentPermissionRepository;

	@Autowired
	private PermissionTypeRepository permissionTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void injectedComponentsAreNotNull(){
		assertThat(documentRepository).isNotNull();
		assertThat(fileTypeRepository).isNotNull();
		assertThat(documentFileRepository).isNotNull();
		assertThat(documentPermissionRepository).isNotNull();
		assertThat(permissionTypeRepository).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(userRepository).isNotNull();
	}

	@Test
	public void UserModel(){
		User u = new User();
		u.setName("Garry");
		u.setUsername("GarryTheSnail03");
		u.setEmail("GarryTheSnail@gmail.com");
		u.setHash(passwordEncoder.encode("goodGarryTheSnail"));
		userRepository.save(u);

		User testing_user = userRepository.findById(u.getUser_id()).orElse(null);
		assertEquals(u.getName(), testing_user.getName());
		assertEquals("garrythesnail03", testing_user.getUsername());
		assertEquals("garrythesnail@gmail.com", testing_user.getEmail());
		assertEquals(false, testing_user.getVerified());
		assertEquals(true, passwordEncoder.matches("goodGarryTheSnail", testing_user.getHash()));

		u.setVerified(true);
		userRepository.save(u);

		User testing_user2 = userRepository.findById(u.getUser_id()).orElse(null);
		assertEquals(true, testing_user2.getVerified());
		assertEquals(1, userRepository.count());

		userRepository.deleteById(u.getUser_id());
		assertEquals(0, userRepository.count());
	}

	@Test
	public void DocumentModel(){
		Document d = new Document();
		d.setTitle("recite");
		d.setDescription("layer->bank");
		d.setAuthor_id(1);
		documentRepository.save(d);

		Document testing_doc = documentRepository.findById(d.getDocument_id()).orElse(null);
		assertEquals(d.getTitle(), testing_doc.getTitle());

		testing_doc.setDescription("layer->bank->accountant");
		documentRepository.save(testing_doc);
		Document testing_doc2 = documentRepository.findById(d.getDocument_id()).orElse(null);
		assertEquals("layer->bank->accountant", testing_doc2.getDescription());
		assertEquals(1, documentRepository.count());

		documentRepository.deleteById(d.getDocument_id());
		assertEquals(0, documentRepository.count());
	}

	@Test
	public void DocumentFileModel(){
		Document d = new Document();
		d.setTitle("recite");
		d.setDescription("layer->bank");
		d.setAuthor_id(1);
		documentRepository.save(d);

		DocumentFile df = new DocumentFile();
		df.setType(1);
		df.setLink("http://somegfrvjgr.com/pic/322342.jpg");
		df.setDocument_id(d.getDocument_id());
		df.setTitle("322342.jpg");
		documentFileRepository.save(df);

		DocumentFile testing_df = documentFileRepository.findById(df.getDocument_file_id()).orElse(null);
		assertEquals(df.getTitle(), testing_df.getTitle());

		testing_df.setTitle("front page");
		documentFileRepository.save(testing_df);
		DocumentFile testing_df2 = documentFileRepository.findById(df.getDocument_file_id()).orElse(null);
		assertEquals("front page", testing_df2.getTitle());
		assertEquals(1, documentFileRepository.count());

		documentFileRepository.deleteById(df.getDocument_file_id());
		assertEquals(0, documentFileRepository.count());
	}

	@Test
	public void FileTypeModel(){
		FileType t = new FileType();
		t.setTitle("jpg");
		fileTypeRepository.save(t);

		FileType testing_t = fileTypeRepository.findById(t.getFile_type_id()).orElse(null);
		assertEquals(t.getTitle(), testing_t.getTitle());

		t.setTitle("png");
		fileTypeRepository.save(t);
		FileType testing_t2 = fileTypeRepository.findById(t.getFile_type_id()).orElse(null);
		assertEquals("png", testing_t2.getTitle());
		assertEquals(1, fileTypeRepository.count());

		fileTypeRepository.deleteById(t.getFile_type_id());
		assertEquals(0, fileTypeRepository.count());
	}

	@Test
	public void PermissionTypeModel(){
		PermissionType t = new PermissionType();
		t.setTitle("edit");
		permissionTypeRepository.save(t);

		PermissionType testing_t = permissionTypeRepository.findById(t.getPermission_type_id()).orElse(null);
		assertEquals(t.getTitle(), testing_t.getTitle());

		t.setTitle("read");
		permissionTypeRepository.save(t);
		PermissionType testing_t2 = permissionTypeRepository.findById(t.getPermission_type_id()).orElse(null);
		assertEquals("read", testing_t2.getTitle());
		assertEquals(1, permissionTypeRepository.count());

		permissionTypeRepository.deleteById(t.getPermission_type_id());
		assertEquals(0, permissionTypeRepository.count());
	}

	@Test
	public void DocumentPermissionModel(){
		DocumentPermission dp = new DocumentPermission();
		dp.setDocument_id(1);
		dp.setDependant_user_id(2);
		dp.setAuthor_id(1);
		dp.setType(1);
		documentPermissionRepository.save(dp);

		DocumentPermission testing_dp = documentPermissionRepository.findById(dp.getDocument_permission_id()).orElse(null);
		assertEquals(dp.getDocument_id(), testing_dp.getDocument_id());
		assertEquals(1, documentPermissionRepository.count());
		documentPermissionRepository.deleteById(dp.getDocument_permission_id());
		assertEquals(0, documentPermissionRepository.count());
	}

}
