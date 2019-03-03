package com.yunforge.core.captcha;

import java.awt.Color;
import java.awt.Font;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

public class EaipCaptchaEngine extends ListImageCaptchaEngine {
	@Override
	protected void buildInitialFactories() {
		WordGenerator wordGenerator = new RandomWordGenerator(
				"aabbccddeefgghhkkmnnooppqqsstuuvvwxxyyzz00112233445566778899");

		TextPaster textPaster = new RandomTextPaster(4, 4, Color.BLACK);

		BackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(
				Integer.valueOf(110), Integer.valueOf(50), Color.white);

		FontGenerator fontGenerator = new RandomFontGenerator(
				Integer.valueOf(28), Integer.valueOf(28), new Font[] {
						new Font("nyala", 1, 28), new Font("Bell MT", 0, 28),
						new Font("Credit valley", 1, 28) }, false);

		WordToImage wordToImage = new ComposedWordToImage(fontGenerator,
				backgroundGenerator, textPaster);

		addFactory(new GimpyFactory(wordGenerator, wordToImage));
	}
}