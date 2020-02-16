.PHONY: all
all: proposal.pdf report.pdf

%.pdf: %.tex references.bib
	@xelatex -halt-on-error $<
	@bibtex $(basename $<).aux
	@xelatex -halt-on-error $<
	@xelatex -halt-on-error $<

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
