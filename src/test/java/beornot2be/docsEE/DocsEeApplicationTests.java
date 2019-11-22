package beornot2be.docsEE;

import beornot2be.docsEE.db.methods.DocumentFileApi;
import beornot2be.docsEE.model.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class DocsEeApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private DocumentFileRepository documentFileRepository;

	@Autowired
	private FileTypeRepository fileTypeRepository;

	@Test
	public void contextLoads() {
		System.out.println("Testing ...");


		FileType ft = new FileType();
		ft.setTitle("img");
		entityManager.persist(ft);
		entityManager.flush();
		FileType ft2 = fileTypeRepository.findById(1).orElse(null);
		assertEquals(ft.getTitle(), ft2.getTitle());


		Document doc1 = new Document();
		doc1.setTitle("new test doc");
		doc1.setDescription("desc of new test doc");
		entityManager.persist(doc1);
		entityManager.flush();
		Document doc2 = documentRepository.findById(1).orElse(null);
		assertEquals(doc1.getTitle(), doc2.getTitle());



		DocumentFile file1 = new DocumentFile();
		file1.setDocument_id(1);
		file1.setTitle("some img");
		file1.setLink("https://instagram.fymy1-1.fna.fbcdn.net/v/t51.2885-15/e35/12547282_1567069960280276_1089750351_n.jpg?_nc_ht=instagram.fymy1-1.fna.fbcdn.net&oh=eb53cfc692080ff47b8c0ee1f8d74a08&oe=5E0A6491&ig_cache_key=MTE3ODYyNjUwMTk5Mzc4MTc2Ng%3D%3D.2");
		file1.setType(ft);

		DocumentFile file2 = new DocumentFile();
		file2.setDocument_id(1);
		file2.setTitle("some img2");
		file2.setLink("https://instagram.fymy1-1.fna.fbcdn.net/v/t51.2885-15/e35/12547282_1567069960280276_1089750351_n.jpg?_nc_ht=instagram.fymy1-1.fna.fbcdn.net&oh=eb53cfc692080ff47b8c0ee1f8d74a08&oe=5E0A6491&ig_cache_key=MTE3ODYyNjUwMTk5Mzc4MTc2Ng%3D%3D.2");
		file2.setType(ft);

		entityManager.persist(file1);
		entityManager.flush();

		entityManager.persist(file2);
		entityManager.flush();

		assertEquals(documentFileRepository.count(), 2);

		System.out.println(file1.getDocument_file_id());


		Set<DocumentFile> files = new HashSet<DocumentFile>();
		files.add(file1);
		files.add(file2);
		doc1.setFiles(files);

		entityManager.merge(doc2);
		entityManager.flush();

		Document doc3 = documentRepository.findById(1).orElse(null);
		assertEquals(doc3.getFiles().orElse(null), files);


	}

}
