.PHONY: all
all: proposal.pdf report.pdf

%.pdf: %.tex references.bib
	@pdflatex -halt-on-error $<
	@bibtex $(basename $<).aux
	@pdflatex -halt-on-error $<
	@pdflatex -halt-on-error $<

.PHONY: clean
clean:
	@rm --force *.nav \
				*.log \
				*.out \
				*.pdf \
				*.toc \
				*.snm \
				*.aux \
				*.bbl \
				*.blg \
				*.vrb
