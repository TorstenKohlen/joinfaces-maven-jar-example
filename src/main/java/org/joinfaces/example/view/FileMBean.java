/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.example.view;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import jakarta.faces.view.ViewScoped;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

/**
 * FileMBean to test primefaces upload component.
 * @author Marcelo Fernandes
 */
@Component
@ViewScoped
public class FileMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressFBWarnings("EI_EXPOSE_REP")
	@Getter
	@Setter
	private transient UploadedFile uploadedFile;

	@Getter
	private transient StreamedContent downloadFile;

	/**
	* Upload file action.
	*/
	public void upload() throws IOException {
		if (this.uploadedFile != null) {
			byte[] data = FileCopyUtils.copyToByteArray(this.uploadedFile.getInputStream());
			this.downloadFile = DefaultStreamedContent.builder()
				.stream(() -> new ByteArrayInputStream(data))
				.contentType(this.uploadedFile.getContentType())
				.name(this.uploadedFile.getFileName())
				.build();
		}
	}
}
