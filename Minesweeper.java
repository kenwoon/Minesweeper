package minesweeper;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Minesweeper extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		startGame(primaryStage);
	}
	
	void startGame(Stage stage) {
		BorderPane root = new BorderPane();
		HBox status = new HBox();
		ImageView smile = new ImageView(new Image("file:res/face-smile.png"));
		ImageView win = new ImageView(new Image("file:res/face-win.png"));
		ImageView dead = new ImageView(new Image("file:res/face-dead.png"));
		smile.setFitWidth(80); smile.setFitHeight(80);
		win.setFitWidth(80); win.setFitHeight(80);
		dead.setFitWidth(80); dead.setFitHeight(80);
		Button smileButton = new Button();
		smileButton.setOnAction(e -> {
			restart(stage);
		});
		smileButton.setGraphic(smile);
		Button winButton = new Button();
		winButton.setOnAction(e -> {
			restart(stage);
		});
		winButton.setGraphic(win);
		Button deadButton = new Button();
		deadButton.setOnAction(e -> {
			restart(stage);
		});
		deadButton.setGraphic(dead);
		ArrayList<Integer> bombsLeft = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		int timeElapsed = 0;
		status.getChildren().add(new CustomPane("" + bombsLeft.size()));
		status.getChildren().add(smileButton);
		status.getChildren().add(new CustomPane("" + timeElapsed));
		
		GridPane tile = new GridPane();
		ImageView cover;
		int[][] tiles;
		ArrayList<Integer> buttonsClicked = new ArrayList<Integer>();
		MineButton buttons[][];
		buttons = new MineButton[8][8];
		tiles = new int[8][8];
		
		int randX, randY;
		for (int i = 0; i < 10; i++) {
			randX = (int)(Math.random() * 8);
			randY = (int)(Math.random() * 8);
			if (tiles[randX][randY] == 9)
				i--;
			tiles[randX][randY] = 9;
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tiles[i][j] != 9) {
					tiles[i][j] = 0;
					if (i > 0) {
						if (j > 0) {
							if (tiles[i-1][j-1] == 9)
								tiles[i][j]++;
						}
						if (tiles[i-1][j] == 9)
							tiles[i][j]++;
						if (j < 7) {
							if (tiles[i-1][j+1] == 9)
								tiles[i][j]++;
						}
					}
					if (j > 0) {
						if (tiles[i][j-1] == 9)
							tiles[i][j]++;
					}
					if (j < 7) {
						if (tiles[i][j+1] == 9)
							tiles[i][j]++;
					}
					if (i < 7) {
						if (j > 0) {
							if (tiles[i+1][j-1] == 9)
								tiles[i][j]++;
						}
						if (tiles[i+1][j] == 9)
							tiles[i][j]++;
						if (j < 7) {
							if (tiles[i+1][j+1] == 9)
								tiles[i][j]++;
						}
					}
				}
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				buttons[i][j] = new MineButton(tiles[i][j]);
				cover = new ImageView(new Image("file:res/cover.png"));
				buttons[i][j].setGraphic(cover);
				cover.setFitWidth(33);
				cover.setFitHeight(33);
				buttons[i][j].setOnMouseClicked(buttons[i][j]);
				if (tiles[i][j] == 9) {
					buttons[i][j].addEventHandler(ActionEvent.ACTION, e -> status.getChildren().remove(1));
					buttons[i][j].addEventHandler(ActionEvent.ACTION, e -> status.getChildren().add(1, deadButton));
					buttons[i][j].addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							for (int k = 0; k < 8; k++) {
								for (int l = 0; l < 8; l++) {
									if (tiles[k][l] == 9) {
										ImageView mines = new ImageView(new Image("file:res/mine-grey.png"));
										mines.setFitWidth(33);
										mines.setFitHeight(33);
										buttons[k][l].setGraphic(mines);
									}
									buttons[k][l].setMouseTransparent(true);
								}
							}
						}
					});
				}
				
				else {
					final int x = i;
					final int y = j;
					final ImageView coverloop = cover;
					buttons[i][j].addEventHandler(ActionEvent.ACTION, e -> buttonsClicked.add(0));
					buttons[i][j].addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
							ImageView image0 = new ImageView(new Image("file:res/0.png"));
							ImageView image1 = new ImageView(new Image("file:res/1.png"));
							ImageView image2 = new ImageView(new Image("file:res/2.png"));
							ImageView image3 = new ImageView(new Image("file:res/3.png"));
							ImageView image4 = new ImageView(new Image("file:res/4.png"));
							ImageView image5 = new ImageView(new Image("file:res/5.png"));
							ImageView image6 = new ImageView(new Image("file:res/6.png"));
							ImageView image7 = new ImageView(new Image("file:res/7.png"));
							ImageView image8 = new ImageView(new Image("file:res/8.png"));
							ImageView redmine = new ImageView(new Image("file:res/mine-red.png"));
							int size = 33;
							image0.setFitWidth(size); image0.setFitHeight(size);
							image1.setFitWidth(size); image1.setFitHeight(size);
							image2.setFitWidth(size); image2.setFitHeight(size);
							image3.setFitWidth(size); image3.setFitHeight(size);
							image4.setFitWidth(size); image4.setFitHeight(size);
							image5.setFitWidth(size); image5.setFitHeight(size);
							image6.setFitWidth(size); image6.setFitHeight(size);
							image7.setFitWidth(size); image7.setFitHeight(size);
							image8.setFitWidth(size); image8.setFitHeight(size);
							redmine.setFitWidth(size); redmine.setFitHeight(size);
							
							if (x > 0) {
								if (y > 0) {
									if (buttons[x-1][y-1].flagstate == 0) {
										if (tiles[x-1][y-1] == 0) {
											image0 = new ImageView(new Image("file:res/0.png"));
											image0.setFitWidth(size); image0.setFitHeight(size);
											buttons[x-1][y-1].setGraphic(image0);
											if (x-1 > 0) {
												if (y-1 > 0) {
													if (buttons[x-2][y-2].flagstate == 0) {
														if (tiles[x-2][y-2] == 0) {
															image0 = new ImageView(new Image("file:res/0.png"));
															image0.setFitWidth(size); image0.setFitHeight(size);
															buttons[x-2][y-2].setGraphic(image0);
														}
														if (tiles[x-2][y-2] == 1) {
															image1 = new ImageView(new Image("file:res/1.png"));
															image1.setFitWidth(size); image1.setFitHeight(size);
															buttons[x-2][y-2].setGraphic(image1);
														}
														if (tiles[x-2][y-2] == 2) {
															image2 = new ImageView(new Image("file:res/2.png"));
															image2.setFitWidth(size); image2.setFitHeight(size);
															buttons[x-2][y-2].setGraphic(image2);
														}
														if (tiles[x-2][y-2] == 3) {
															image3 = new ImageView(new Image("file:res/3.png"));
															image3.setFitWidth(size); image3.setFitHeight(size);
															buttons[x-2][y-2].setGraphic(image3);
														}
														if (tiles[x-2][y-2] == 4) {
															image4 = new ImageView(new Image("file:res/4.png"));
															image4.setFitWidth(size); image4.setFitHeight(size);
															buttons[x-2][y-2].setGraphic(image4);
														}
														if (tiles[x-2][y-2] == 5) {
															image5 = new ImageView(new Image("file:res/5.png"));
															image5.setFitWidth(size); image5.setFitHeight(size);
															buttons[x-2][y-2].setGraphic(image5);
														}
														if (tiles[x-2][y-2] == 6) {
															image6 = new ImageView(new Image("file:res/6.png"));
															image6.setFitWidth(size); image6.setFitHeight(size);
															buttons[x-2][y-2].setGraphic(image6);
														}
														if (tiles[x-2][y-2] == 7) {
															image7 = new ImageView(new Image("file:res/7.png"));
															image7.setFitWidth(size); image7.setFitHeight(size);
															buttons[x-2][y-2].setGraphic(image7);
														}
														if (tiles[x-2][y-2] == 8) {
															image8 = new ImageView(new Image("file:res/8.png"));
															image8.setFitWidth(size); image8.setFitHeight(size);
															buttons[x-2][y-2].setGraphic(image8);
														}
														buttons[x-2][y-2].flagstate = 2;
													}
												}
												
												if (buttons[x-2][y-1].flagstate == 0) {
													if (tiles[x-2][y-1] == 0) {
														image0 = new ImageView(new Image("file:res/0.png"));
														image0.setFitWidth(size); image0.setFitHeight(size);
														buttons[x-2][y-1].setGraphic(image0);
													}
													if (tiles[x-2][y-1] == 1) {
														image1 = new ImageView(new Image("file:res/1.png"));
														image1.setFitWidth(size); image1.setFitHeight(size);
														buttons[x-2][y-1].setGraphic(image1);
													}
													if (tiles[x-2][y-1] == 2) {
														image2 = new ImageView(new Image("file:res/2.png"));
														image2.setFitWidth(size); image2.setFitHeight(size);
														buttons[x-2][y-1].setGraphic(image2);
													}
													if (tiles[x-2][y-1] == 3) {
														image3 = new ImageView(new Image("file:res/3.png"));
														image3.setFitWidth(size); image3.setFitHeight(size);
														buttons[x-2][y-1].setGraphic(image3);
													}
													if (tiles[x-2][y-1] == 4) {
														image4 = new ImageView(new Image("file:res/4.png"));
														image4.setFitWidth(size); image4.setFitHeight(size);
														buttons[x-2][y-1].setGraphic(image4);
													}
													if (tiles[x-2][y-1] == 5) {
														image5 = new ImageView(new Image("file:res/5.png"));
														image5.setFitWidth(size); image5.setFitHeight(size);
														buttons[x-2][y-1].setGraphic(image5);
													}
													if (tiles[x-2][y-1] == 6) {
														image6 = new ImageView(new Image("file:res/6.png"));
														image6.setFitWidth(size); image6.setFitHeight(size);
														buttons[x-2][y-1].setGraphic(image6);
													}
													if (tiles[x-2][y-1] == 7) {
														image7 = new ImageView(new Image("file:res/7.png"));
														image7.setFitWidth(size); image7.setFitHeight(size);
														buttons[x-2][y-1].setGraphic(image7);
													}
													if (tiles[x-2][y-1] == 8) {
														image8 = new ImageView(new Image("file:res/8.png"));
														image8.setFitWidth(size); image8.setFitHeight(size);
														buttons[x-2][y-1].setGraphic(image8);
													}
													buttons[x-2][y-1].flagstate = 2;
												}
												
												if (buttons[x-2][y].flagstate == 0) {
													if (tiles[x-2][y] == 0) {
														image0 = new ImageView(new Image("file:res/0.png"));
														image0.setFitWidth(size); image0.setFitHeight(size);
														buttons[x-2][y].setGraphic(image0);
													}
													if (tiles[x-2][y] == 1) {
														image1 = new ImageView(new Image("file:res/1.png"));
														image1.setFitWidth(size); image1.setFitHeight(size);
														buttons[x-2][y].setGraphic(image1);
													}
													if (tiles[x-2][y] == 2) {
														image2 = new ImageView(new Image("file:res/2.png"));
														image2.setFitWidth(size); image2.setFitHeight(size);
														buttons[x-2][y].setGraphic(image2);
													}
													if (tiles[x-2][y] == 3) {
														image3 = new ImageView(new Image("file:res/3.png"));
														image3.setFitWidth(size); image3.setFitHeight(size);
														buttons[x-2][y].setGraphic(image3);
													}
													if (tiles[x-2][y] == 4) {
														image4 = new ImageView(new Image("file:res/4.png"));
														image4.setFitWidth(size); image4.setFitHeight(size);
														buttons[x-2][y].setGraphic(image4);
													}
													if (tiles[x-2][y] == 5) {
														image5 = new ImageView(new Image("file:res/5.png"));
														image5.setFitWidth(size); image5.setFitHeight(size);
														buttons[x-2][y].setGraphic(image5);
													}
													if (tiles[x-2][y] == 6) {
														image6 = new ImageView(new Image("file:res/6.png"));
														image6.setFitWidth(size); image6.setFitHeight(size);
														buttons[x-2][y].setGraphic(image6);
													}
													if (tiles[x-2][y] == 7) {
														image7 = new ImageView(new Image("file:res/7.png"));
														image7.setFitWidth(size); image7.setFitHeight(size);
														buttons[x-2][y].setGraphic(image7);
													}
													if (tiles[x-2][y] == 8) {
														image8 = new ImageView(new Image("file:res/8.png"));
														image8.setFitWidth(size); image8.setFitHeight(size);
														buttons[x-2][y].setGraphic(image8);
													}
													buttons[x-2][y].flagstate = 2;
												}		
											}
											
											if (y-1 > 0) {
												if (buttons[x-1][y-2].flagstate == 0) {
													if (tiles[x-1][y-2] == 0) {
														image0 = new ImageView(new Image("file:res/0.png"));
														image0.setFitWidth(size); image0.setFitHeight(size);
														buttons[x-1][y-2].setGraphic(image0);
													}
													if (tiles[x-1][y-2] == 1) {
														image1 = new ImageView(new Image("file:res/1.png"));
														image1.setFitWidth(size); image1.setFitHeight(size);
														buttons[x-1][y-2].setGraphic(image1);
													}
													if (tiles[x-1][y-2] == 2) {
														image2 = new ImageView(new Image("file:res/2.png"));
														image2.setFitWidth(size); image2.setFitHeight(size);
														buttons[x-1][y-2].setGraphic(image2);
													}
													if (tiles[x-1][y-2] == 3) {
														image3 = new ImageView(new Image("file:res/3.png"));
														image3.setFitWidth(size); image3.setFitHeight(size);
														buttons[x-1][y-2].setGraphic(image3);
													}
													if (tiles[x-1][y-2] == 4) {
														image4 = new ImageView(new Image("file:res/4.png"));
														image4.setFitWidth(size); image4.setFitHeight(size);
														buttons[x-1][y-2].setGraphic(image4);
													}
													if (tiles[x-1][y-2] == 5) {
														image5 = new ImageView(new Image("file:res/5.png"));
														image5.setFitWidth(size); image5.setFitHeight(size);
														buttons[x-1][y-2].setGraphic(image5);
													}
													if (tiles[x-1][y-2] == 6) {
														image6 = new ImageView(new Image("file:res/6.png"));
														image6.setFitWidth(size); image6.setFitHeight(size);
														buttons[x-1][y-2].setGraphic(image6);
													}
													if (tiles[x-1][y-2] == 7) {
														image7 = new ImageView(new Image("file:res/7.png"));
														image7.setFitWidth(size); image7.setFitHeight(size);
														buttons[x-1][y-2].setGraphic(image7);
													}
													if (tiles[x-1][y-2] == 8) {
														image8 = new ImageView(new Image("file:res/8.png"));
														image8.setFitWidth(size); image8.setFitHeight(size);
														buttons[x-1][y-2].setGraphic(image8);
													}
													buttons[x-1][y-2].flagstate = 2;
												}
											}
										
											if (y-1 > 0) {
												if (buttons[x][y-2].flagstate == 0) {
													if (tiles[x][y-2] == 0) {
														image0 = new ImageView(new Image("file:res/0.png"));
														image0.setFitWidth(size); image0.setFitHeight(size);
														buttons[x][y-2].setGraphic(image0);
													}
													if (tiles[x][y-2] == 1) {
														image1 = new ImageView(new Image("file:res/1.png"));
														image1.setFitWidth(size); image1.setFitHeight(size);
														buttons[x][y-2].setGraphic(image1);
													}
													if (tiles[x][y-2] == 2) {
														image2 = new ImageView(new Image("file:res/2.png"));
														image2.setFitWidth(size); image2.setFitHeight(size);
														buttons[x][y-2].setGraphic(image2);
													}
													if (tiles[x][y-2] == 3) {
														image3 = new ImageView(new Image("file:res/3.png"));
														image3.setFitWidth(size); image3.setFitHeight(size);
														buttons[x][y-2].setGraphic(image3);
													}
													if (tiles[x][y-2] == 4) {
														image4 = new ImageView(new Image("file:res/4.png"));
														image4.setFitWidth(size); image4.setFitHeight(size);
														buttons[x][y-2].setGraphic(image4);
													}
													if (tiles[x][y-2] == 5) {
														image5 = new ImageView(new Image("file:res/5.png"));
														image5.setFitWidth(size); image5.setFitHeight(size);
														buttons[x][y-2].setGraphic(image5);
													}
													if (tiles[x][y-2] == 6) {
														image6 = new ImageView(new Image("file:res/6.png"));
														image6.setFitWidth(size); image6.setFitHeight(size);
														buttons[x+1][y-2].setGraphic(image6);
													}
													if (tiles[x][y-2] == 7) {
														image7 = new ImageView(new Image("file:res/7.png"));
														image7.setFitWidth(size); image7.setFitHeight(size);
														buttons[x][y-2].setGraphic(image7);
													}
													if (tiles[x][y-2] == 8) {
														image8 = new ImageView(new Image("file:res/8.png"));
														image8.setFitWidth(size); image8.setFitHeight(size);
														buttons[x][y-2].setGraphic(image8);
													}
													buttons[x][y-2].flagstate = 2;
												}
											}		
										}
										if (tiles[x-1][y-1] == 1) {
											image1 = new ImageView(new Image("file:res/1.png"));
											image1.setFitWidth(size); image1.setFitHeight(size);
											buttons[x-1][y-1].setGraphic(image1);
										}
										if (tiles[x-1][y-1] == 2) {
											image2 = new ImageView(new Image("file:res/2.png"));
											image2.setFitWidth(size); image2.setFitHeight(size);
											buttons[x-1][y-1].setGraphic(image2);
										}
										if (tiles[x-1][y-1] == 3) {
											image3 = new ImageView(new Image("file:res/3.png"));
											image3.setFitWidth(size); image3.setFitHeight(size);
											buttons[x-1][y-1].setGraphic(image3);
										}
										if (tiles[x-1][y-1] == 4) {
											image4 = new ImageView(new Image("file:res/4.png"));
											image4.setFitWidth(size); image4.setFitHeight(size);
											buttons[x-1][y-1].setGraphic(image4);
										}
										if (tiles[x-1][y-1] == 5) {
											image5 = new ImageView(new Image("file:res/5.png"));
											image5.setFitWidth(size); image5.setFitHeight(size);
											buttons[x-1][y-1].setGraphic(image5);
										}
										if (tiles[x-1][y-1] == 6) {
											image6 = new ImageView(new Image("file:res/6.png"));
											image6.setFitWidth(size); image6.setFitHeight(size);
											buttons[x-1][y-1].setGraphic(image6);
										}
										if (tiles[x-1][y-1] == 7) {
											image7 = new ImageView(new Image("file:res/7.png"));
											image7.setFitWidth(size); image7.setFitHeight(size);
											buttons[x-1][y-1].setGraphic(image7);
										}
										if (tiles[x-1][y-1] == 8) {
											image8 = new ImageView(new Image("file:res/8.png"));
											image8.setFitWidth(size); image8.setFitHeight(size);
											buttons[x-1][y-1].setGraphic(image8);
										}
										buttons[x-1][y-1].flagstate = 2;
									}
								}
								
								if (buttons[x-1][y].flagstate == 0) {
									if (tiles[x-1][y] == 0) {
										image0 = new ImageView(new Image("file:res/0.png"));
										image0.setFitWidth(size); image0.setFitHeight(size);
										buttons[x-1][y].setGraphic(image0);
										if (x-1 > 0) {
											if (buttons[x-2][y].flagstate == 0) {
												if (tiles[x-2][y] == 0) {
													image0 = new ImageView(new Image("file:res/0.png"));
													image0.setFitWidth(size); image0.setFitHeight(size);
													buttons[x-2][y].setGraphic(image0);
												}
												if (tiles[x-2][y] == 1) {
													image1 = new ImageView(new Image("file:res/1.png"));
													image1.setFitWidth(size); image1.setFitHeight(size);
													buttons[x-2][y].setGraphic(image1);
												}
												if (tiles[x-2][y] == 2) {
													image2 = new ImageView(new Image("file:res/2.png"));
													image2.setFitWidth(size); image2.setFitHeight(size);
													buttons[x-2][y].setGraphic(image2);
												}
												if (tiles[x-2][y] == 3) {
													image3 = new ImageView(new Image("file:res/3.png"));
													image3.setFitWidth(size); image3.setFitHeight(size);
													buttons[x-2][y].setGraphic(image3);
												}
												if (tiles[x-2][y] == 4) {
													image4 = new ImageView(new Image("file:res/4.png"));
													image4.setFitWidth(size); image4.setFitHeight(size);
													buttons[x-2][y].setGraphic(image4);
												}
												if (tiles[x-2][y] == 5) {
													image5 = new ImageView(new Image("file:res/5.png"));
													image5.setFitWidth(size); image5.setFitHeight(size);
													buttons[x-2][y].setGraphic(image5);
												}
												if (tiles[x-2][y] == 6) {
													image6 = new ImageView(new Image("file:res/6.png"));
													image6.setFitWidth(size); image6.setFitHeight(size);
													buttons[x-2][y].setGraphic(image6);
												}
												if (tiles[x-2][y] == 7) {
													image7 = new ImageView(new Image("file:res/7.png"));
													image7.setFitWidth(size); image7.setFitHeight(size);
													buttons[x-2][y].setGraphic(image7);
												}
												if (tiles[x-2][y] == 8) {
													image8 = new ImageView(new Image("file:res/8.png"));
													image8.setFitWidth(size); image8.setFitHeight(size);
													buttons[x-2][y].setGraphic(image8);
												}
												buttons[x-2][y].flagstate = 2;
											}
										}
									}
									if (tiles[x-1][y] == 1) {
										image1 = new ImageView(new Image("file:res/1.png"));
										image1.setFitWidth(size); image1.setFitHeight(size);
										buttons[x-1][y].setGraphic(image1);
									}
									if (tiles[x-1][y] == 2) {
										image2 = new ImageView(new Image("file:res/2.png"));
										image2.setFitWidth(size); image2.setFitHeight(size);
										buttons[x-1][y].setGraphic(image2);
									}
									if (tiles[x-1][y] == 3) {
										image3 = new ImageView(new Image("file:res/3.png"));
										image3.setFitWidth(size); image3.setFitHeight(size);
										buttons[x-1][y].setGraphic(image3);
									}
									if (tiles[x-1][y] == 4) {
										image4 = new ImageView(new Image("file:res/4.png"));
										image4.setFitWidth(size); image4.setFitHeight(size);
										buttons[x-1][y].setGraphic(image4);
									}
									if (tiles[x-1][y] == 5) {
										image5 = new ImageView(new Image("file:res/5.png"));
										image5.setFitWidth(size); image5.setFitHeight(size);
										buttons[x-1][y].setGraphic(image5);
									}
									if (tiles[x-1][y] == 6) {
										image6 = new ImageView(new Image("file:res/6.png"));
										image6.setFitWidth(size); image6.setFitHeight(size);
										buttons[x-1][y].setGraphic(image6);
									}
									if (tiles[x-1][y] == 7) {
										image7 = new ImageView(new Image("file:res/7.png"));
										image7.setFitWidth(size); image7.setFitHeight(size);
										buttons[x-1][y].setGraphic(image7);
									}
									if (tiles[x-1][y] == 8) {
										image8 = new ImageView(new Image("file:res/8.png"));
										image8.setFitWidth(size); image8.setFitHeight(size);
										buttons[x-1][y].setGraphic(image8);
									}
									buttons[x-1][y].flagstate = 2;
								}
								
								if (y < 7) {
									if (buttons[x-1][y+1].flagstate == 0) {
										if (tiles[x-1][y+1] == 0) {
											image0 = new ImageView(new Image("file:res/0.png"));
											image0.setFitWidth(size); image0.setFitHeight(size);
											buttons[x-1][y+1].setGraphic(image0);
											if (x-1 > 0) {
												if (y < 7) {
													if (buttons[x-2][y+1].flagstate == 0) {
														if (tiles[x-2][y+1] == 0) {
															image0 = new ImageView(new Image("file:res/0.png"));
															image0.setFitWidth(size); image0.setFitHeight(size);
															buttons[x-2][y+1].setGraphic(image0);
														}
														if (tiles[x-2][y+1] == 1) {
															image1 = new ImageView(new Image("file:res/1.png"));
															image1.setFitWidth(size); image1.setFitHeight(size);
															buttons[x-2][y+1].setGraphic(image1);
														}
														if (tiles[x-2][y+1] == 2) {
															image2 = new ImageView(new Image("file:res/2.png"));
															image2.setFitWidth(size); image2.setFitHeight(size);
															buttons[x-2][y+1].setGraphic(image2);
														}
														if (tiles[x-2][y+1] == 3) {
															image3 = new ImageView(new Image("file:res/3.png"));
															image3.setFitWidth(size); image3.setFitHeight(size);
															buttons[x-2][y+1].setGraphic(image3);
														}
														if (tiles[x-2][y+1] == 4) {
															image4 = new ImageView(new Image("file:res/4.png"));
															image4.setFitWidth(size); image4.setFitHeight(size);
															buttons[x-2][y+1].setGraphic(image4);
														}
														if (tiles[x-2][y+1] == 5) {
															image5 = new ImageView(new Image("file:res/5.png"));
															image5.setFitWidth(size); image5.setFitHeight(size);
															buttons[x-2][y+1].setGraphic(image5);
														}
														if (tiles[x-2][y+1] == 6) {
															image6 = new ImageView(new Image("file:res/6.png"));
															image6.setFitWidth(size); image6.setFitHeight(size);
															buttons[x-2][y+1].setGraphic(image6);
														}
														if (tiles[x-2][y+1] == 7) {
															image7 = new ImageView(new Image("file:res/7.png"));
															image7.setFitWidth(size); image7.setFitHeight(size);
															buttons[x-2][y+1].setGraphic(image7);
														}
														if (tiles[x-2][y+1] == 8) {
															image8 = new ImageView(new Image("file:res/8.png"));
															image8.setFitWidth(size); image8.setFitHeight(size);
															buttons[x-2][y+1].setGraphic(image8);
														}
														buttons[x-2][y+1].flagstate = 2;
													}
												}
												if (y+1 < 7) {
													if (buttons[x-2][y+2].flagstate == 0) {
														if (tiles[x-2][y+2] == 0) {
															image0 = new ImageView(new Image("file:res/0.png"));
															image0.setFitWidth(size); image0.setFitHeight(size);
															buttons[x-2][y+2].setGraphic(image0);
														}
														if (tiles[x-2][y+2] == 1) {
															image1 = new ImageView(new Image("file:res/1.png"));
															image1.setFitWidth(size); image1.setFitHeight(size);
															buttons[x-2][y+2].setGraphic(image1);
														}
														if (tiles[x-2][y+2] == 2) {
															image2 = new ImageView(new Image("file:res/2.png"));
															image2.setFitWidth(size); image2.setFitHeight(size);
															buttons[x-2][y+2].setGraphic(image2);
														}
														if (tiles[x-2][y+2] == 3) {
															image3 = new ImageView(new Image("file:res/3.png"));
															image3.setFitWidth(size); image3.setFitHeight(size);
															buttons[x-2][y+2].setGraphic(image3);
														}
														if (tiles[x-2][y+2] == 4) {
															image4 = new ImageView(new Image("file:res/4.png"));
															image4.setFitWidth(size); image4.setFitHeight(size);
															buttons[x-2][y+2].setGraphic(image4);
														}
														if (tiles[x-2][y+2] == 5) {
															image5 = new ImageView(new Image("file:res/5.png"));
															image5.setFitWidth(size); image5.setFitHeight(size);
															buttons[x-2][y+2].setGraphic(image5);
														}
														if (tiles[x-2][y+2] == 6) {
															image6 = new ImageView(new Image("file:res/6.png"));
															image6.setFitWidth(size); image6.setFitHeight(size);
															buttons[x-2][y+2].setGraphic(image6);
														}
														if (tiles[x-2][y+2] == 7) {
															image7 = new ImageView(new Image("file:res/7.png"));
															image7.setFitWidth(size); image7.setFitHeight(size);
															buttons[x-2][y+2].setGraphic(image7);
														}
														if (tiles[x-2][y+2] == 8) {
															image8 = new ImageView(new Image("file:res/8.png"));
															image8.setFitWidth(size); image8.setFitHeight(size);
															buttons[x-2][y+2].setGraphic(image8);
														}
														buttons[x-2][y+2].flagstate = 2;
													}
												}
												
												if (y+1 < 7) {
													if (buttons[x-1][y+2].flagstate == 0) {
														if (tiles[x-1][y+2] == 0) {
															image0 = new ImageView(new Image("file:res/0.png"));
															image0.setFitWidth(size); image0.setFitHeight(size);
															buttons[x-1][y+2].setGraphic(image0);
														}
														if (tiles[x-1][y+2] == 1) {
															image1 = new ImageView(new Image("file:res/1.png"));
															image1.setFitWidth(size); image1.setFitHeight(size);
															buttons[x-1][y+2].setGraphic(image1);
														}
														if (tiles[x-1][y+2] == 2) {
															image2 = new ImageView(new Image("file:res/2.png"));
															image2.setFitWidth(size); image2.setFitHeight(size);
															buttons[x-1][y+2].setGraphic(image2);
														}
														if (tiles[x-1][y+2] == 3) {
															image3 = new ImageView(new Image("file:res/3.png"));
															image3.setFitWidth(size); image3.setFitHeight(size);
															buttons[x-1][y+2].setGraphic(image3);
														}
														if (tiles[x-1][y+2] == 4) {
															image4 = new ImageView(new Image("file:res/4.png"));
															image4.setFitWidth(size); image4.setFitHeight(size);
															buttons[x-1][y+2].setGraphic(image4);
														}
														if (tiles[x-1][y+2] == 5) {
															image5 = new ImageView(new Image("file:res/5.png"));
															image5.setFitWidth(size); image5.setFitHeight(size);
															buttons[x-1][y+2].setGraphic(image5);
														}
														if (tiles[x-1][y+2] == 6) {
															image6 = new ImageView(new Image("file:res/6.png"));
															image6.setFitWidth(size); image6.setFitHeight(size);
															buttons[x-1][y+2].setGraphic(image6);
														}
														if (tiles[x-1][y+2] == 7) {
															image7 = new ImageView(new Image("file:res/7.png"));
															image7.setFitWidth(size); image7.setFitHeight(size);
															buttons[x-1][y+2].setGraphic(image7);
														}
														if (tiles[x-1][y+2] == 8) {
															image8 = new ImageView(new Image("file:res/8.png"));
															image8.setFitWidth(size); image8.setFitHeight(size);
															buttons[x-1][y+2].setGraphic(image8);
														}
														buttons[x-1][y+2].flagstate = 2;
													}
												}
											}
										
											if (y+1 < 7) {
												if (buttons[x][y+2].flagstate == 0) {
													if (tiles[x][y+2] == 0) {
														image0 = new ImageView(new Image("file:res/0.png"));
														image0.setFitWidth(size); image0.setFitHeight(size);
														buttons[x][y+2].setGraphic(image0);
													}
													if (tiles[x][y+2] == 1) {
														image1 = new ImageView(new Image("file:res/1.png"));
														image1.setFitWidth(size); image1.setFitHeight(size);
														buttons[x][y+2].setGraphic(image1);
													}
													if (tiles[x][y+2] == 2) {
														image2 = new ImageView(new Image("file:res/2.png"));
														image2.setFitWidth(size); image2.setFitHeight(size);
														buttons[x][y+2].setGraphic(image2);
													}
													if (tiles[x][y+2] == 3) {
														image3 = new ImageView(new Image("file:res/3.png"));
														image3.setFitWidth(size); image3.setFitHeight(size);
														buttons[x][y+2].setGraphic(image3);
													}
													if (tiles[x][y+2] == 4) {
														image4 = new ImageView(new Image("file:res/4.png"));
														image4.setFitWidth(size); image4.setFitHeight(size);
														buttons[x][y+2].setGraphic(image4);
													}
													if (tiles[x][y+2] == 5) {
														image5 = new ImageView(new Image("file:res/5.png"));
														image5.setFitWidth(size); image5.setFitHeight(size);
														buttons[x][y+2].setGraphic(image5);
													}
													if (tiles[x][y+2] == 6) {
														image6 = new ImageView(new Image("file:res/6.png"));
														image6.setFitWidth(size); image6.setFitHeight(size);
														buttons[x][y+2].setGraphic(image6);
													}
													if (tiles[x][y+2] == 7) {
														image7 = new ImageView(new Image("file:res/7.png"));
														image7.setFitWidth(size); image7.setFitHeight(size);
														buttons[x][y+2].setGraphic(image7);
													}
													if (tiles[x][y+2] == 8) {
														image8 = new ImageView(new Image("file:res/8.png"));
														image8.setFitWidth(size); image8.setFitHeight(size);
														buttons[x][y+2].setGraphic(image8);
													}
													buttons[x][y+2].flagstate = 2;
												}
											}
										}
										if (tiles[x-1][y+1] == 1) {
											image1 = new ImageView(new Image("file:res/1.png"));
											image1.setFitWidth(size); image1.setFitHeight(size);
											buttons[x-1][y+1].setGraphic(image1);
										}
										if (tiles[x-1][y+1] == 2) {
											image2 = new ImageView(new Image("file:res/2.png"));
											image2.setFitWidth(size); image2.setFitHeight(size);
											buttons[x-1][y+1].setGraphic(image2);
										}
										if (tiles[x-1][y+1] == 3) {
											image3 = new ImageView(new Image("file:res/3.png"));
											image3.setFitWidth(size); image3.setFitHeight(size);
											buttons[x-1][y+1].setGraphic(image3);
										}
										if (tiles[x-1][y+1] == 4) {
											image4 = new ImageView(new Image("file:res/4.png"));
											image4.setFitWidth(size); image4.setFitHeight(size);
											buttons[x-1][y+1].setGraphic(image4);
										}
										if (tiles[x-1][y+1] == 5) {
											image5 = new ImageView(new Image("file:res/5.png"));
											image5.setFitWidth(size); image5.setFitHeight(size);
											buttons[x-1][y+1].setGraphic(image5);
										}
										if (tiles[x-1][y+1] == 6) {
											image6 = new ImageView(new Image("file:res/6.png"));
											image6.setFitWidth(size); image6.setFitHeight(size);
											buttons[x-1][y+1].setGraphic(image6);
										}
										if (tiles[x-1][y+1] == 7) {
											image7 = new ImageView(new Image("file:res/7.png"));
											image7.setFitWidth(size); image7.setFitHeight(size);
											buttons[x-1][y+1].setGraphic(image7);
										}
										if (tiles[x-1][y+1] == 8) {
											image8 = new ImageView(new Image("file:res/8.png"));
											image8.setFitWidth(size); image8.setFitHeight(size);
											buttons[x-1][y+1].setGraphic(image8);
										}
										buttons[x-1][y+1].flagstate = 2;
									}
								}
							}
							
							if (y > 0) {
								if (buttons[x][y-1].flagstate == 0) {
									if (tiles[x][y-1] == 0) {
										image0 = new ImageView(new Image("file:res/0.png"));
										image0.setFitWidth(size); image0.setFitHeight(size);
										buttons[x][y-1].setGraphic(image0);
										if (y-1 > 0) {
											if (buttons[x][y-2].flagstate == 0) {
												if (tiles[x][y-2] == 0) {
													image0 = new ImageView(new Image("file:res/0.png"));
													image0.setFitWidth(size); image0.setFitHeight(size);
													buttons[x][y-2].setGraphic(image0);
												}
												if (tiles[x][y-2] == 1) {
													image1 = new ImageView(new Image("file:res/1.png"));
													image1.setFitWidth(size); image1.setFitHeight(size);
													buttons[x][y-2].setGraphic(image1);
												}
												if (tiles[x][y-2] == 2) {
													image2 = new ImageView(new Image("file:res/2.png"));
													image2.setFitWidth(size); image2.setFitHeight(size);
													buttons[x][y-2].setGraphic(image2);
												}
												if (tiles[x][y-2] == 3) {
													image3 = new ImageView(new Image("file:res/3.png"));
													image3.setFitWidth(size); image3.setFitHeight(size);
													buttons[x][y-2].setGraphic(image3);
												}
												if (tiles[x][y-2] == 4) {
													image4 = new ImageView(new Image("file:res/4.png"));
													image4.setFitWidth(size); image4.setFitHeight(size);
													buttons[x][y-2].setGraphic(image4);
												}
												if (tiles[x][y-2] == 5) {
													image5 = new ImageView(new Image("file:res/5.png"));
													image5.setFitWidth(size); image5.setFitHeight(size);
													buttons[x][y-2].setGraphic(image5);
												}
												if (tiles[x][y-2] == 6) {
													image6 = new ImageView(new Image("file:res/6.png"));
													image6.setFitWidth(size); image6.setFitHeight(size);
													buttons[x][y-2].setGraphic(image6);
												}
												if (tiles[x][y-2] == 7) {
													image7 = new ImageView(new Image("file:res/7.png"));
													image7.setFitWidth(size); image7.setFitHeight(size);
													buttons[x][y-2].setGraphic(image7);
												}
												if (tiles[x][y-2] == 8) {
													image8 = new ImageView(new Image("file:res/8.png"));
													image8.setFitWidth(size); image8.setFitHeight(size);
													buttons[x][y-2].setGraphic(image8);
												}
												buttons[x][y-2].flagstate = 2;
											}
										}
									}
									if (tiles[x][y-1] == 1) {
										image1 = new ImageView(new Image("file:res/1.png"));
										image1.setFitWidth(size); image1.setFitHeight(size);
										buttons[x][y-1].setGraphic(image1);
									}
									if (tiles[x][y-1] == 2) {
										image2 = new ImageView(new Image("file:res/2.png"));
										image2.setFitWidth(size); image2.setFitHeight(size);
										buttons[x][y-1].setGraphic(image2);
									}
									if (tiles[x][y-1] == 3) {
										image3 = new ImageView(new Image("file:res/3.png"));
										image3.setFitWidth(size); image3.setFitHeight(size);
										buttons[x][y-1].setGraphic(image3);
									}
									if (tiles[x][y-1] == 4) {
										image4 = new ImageView(new Image("file:res/4.png"));
										image4.setFitWidth(size); image4.setFitHeight(size);
										buttons[x][y-1].setGraphic(image4);
									}
									if (tiles[x][y-1] == 5) {
										image5 = new ImageView(new Image("file:res/5.png"));
										image5.setFitWidth(size); image5.setFitHeight(size);
										buttons[x][y-1].setGraphic(image5);
									}
									if (tiles[x][y-1] == 6) {
										image6 = new ImageView(new Image("file:res/6.png"));
										image6.setFitWidth(size); image6.setFitHeight(size);
										buttons[x][y-1].setGraphic(image6);
									}
									if (tiles[x][y-1] == 7) {
										image7 = new ImageView(new Image("file:res/7.png"));
										image7.setFitWidth(size); image7.setFitHeight(size);
										buttons[x][y-1].setGraphic(image7);
									}
									if (tiles[x][y-1] == 8) {
										image8 = new ImageView(new Image("file:res/8.png"));
										image8.setFitWidth(size); image8.setFitHeight(size);
										buttons[x][y-1].setGraphic(image8);
									}
									buttons[x][y-1].flagstate = 2;
								}
							}
							
							if (y < 7) {
								if (buttons[x][y+1].flagstate == 0) {
									if (tiles[x][y+1] == 0) {
										image0 = new ImageView(new Image("file:res/0.png"));
										image0.setFitWidth(size); image0.setFitHeight(size);
										buttons[x][y+1].setGraphic(image0);
										if (y+1 < 7) {
											if (buttons[x][y+2].flagstate == 0) {
												if (tiles[x][y+2] == 0) {
													image0 = new ImageView(new Image("file:res/0.png"));
													image0.setFitWidth(size); image0.setFitHeight(size);
													buttons[x][y+2].setGraphic(image0);
												}
												if (tiles[x][y+2] == 1) {
													image1 = new ImageView(new Image("file:res/1.png"));
													image1.setFitWidth(size); image1.setFitHeight(size);
													buttons[x][y+2].setGraphic(image1);
												}
												if (tiles[x][y+2] == 2) {
													image2 = new ImageView(new Image("file:res/2.png"));
													image2.setFitWidth(size); image2.setFitHeight(size);
													buttons[x][y+2].setGraphic(image2);
												}
												if (tiles[x][y+2] == 3) {
													image3 = new ImageView(new Image("file:res/3.png"));
													image3.setFitWidth(size); image3.setFitHeight(size);
													buttons[x][y+2].setGraphic(image3);
												}
												if (tiles[x][y+2] == 4) {
													image4 = new ImageView(new Image("file:res/4.png"));
													image4.setFitWidth(size); image4.setFitHeight(size);
													buttons[x][y+2].setGraphic(image4);
												}
												if (tiles[x][y+2] == 5) {
													image5 = new ImageView(new Image("file:res/5.png"));
													image5.setFitWidth(size); image5.setFitHeight(size);
													buttons[x][y+2].setGraphic(image5);
												}
												if (tiles[x][y+2] == 6) {
													image6 = new ImageView(new Image("file:res/6.png"));
													image6.setFitWidth(size); image6.setFitHeight(size);
													buttons[x][y+2].setGraphic(image6);
												}
												if (tiles[x][y+2] == 7) {
													image7 = new ImageView(new Image("file:res/7.png"));
													image7.setFitWidth(size); image7.setFitHeight(size);
													buttons[x][y+2].setGraphic(image7);
												}
												if (tiles[x][y+2] == 8) {
													image8 = new ImageView(new Image("file:res/8.png"));
													image8.setFitWidth(size); image8.setFitHeight(size);
													buttons[x][y+2].setGraphic(image8);
												}
												buttons[x][y+2].flagstate = 2;
											}
										}
									}
									if (tiles[x][y+1] == 1) {
										image1 = new ImageView(new Image("file:res/1.png"));
										image1.setFitWidth(size); image1.setFitHeight(size);
										buttons[x][y+1].setGraphic(image1);
									}
									if (tiles[x][y+1] == 2) {
										image2 = new ImageView(new Image("file:res/2.png"));
										image2.setFitWidth(size); image2.setFitHeight(size);
										buttons[x][y+1].setGraphic(image2);
									}
									if (tiles[x][y+1] == 3) {
										image3 = new ImageView(new Image("file:res/3.png"));
										image3.setFitWidth(size); image3.setFitHeight(size);
										buttons[x][y+1].setGraphic(image3);
									}
									if (tiles[x][y+1] == 4) {
										image4 = new ImageView(new Image("file:res/4.png"));
										image4.setFitWidth(size); image4.setFitHeight(size);
										buttons[x][y+1].setGraphic(image4);
									}
									if (tiles[x][y+1] == 5) {
										image5 = new ImageView(new Image("file:res/5.png"));
										image5.setFitWidth(size); image5.setFitHeight(size);
										buttons[x][y+1].setGraphic(image5);
									}
									if (tiles[x][y+1] == 6) {
										image6 = new ImageView(new Image("file:res/6.png"));
										image6.setFitWidth(size); image6.setFitHeight(size);
										buttons[x][y+1].setGraphic(image6);
									}
									if (tiles[x][y+1] == 7) {
										image7 = new ImageView(new Image("file:res/7.png"));
										image7.setFitWidth(size); image7.setFitHeight(size);
										buttons[x][y+1].setGraphic(image7);
									}
									if (tiles[x][y+1] == 8) {
										image8 = new ImageView(new Image("file:res/8.png"));
										image8.setFitWidth(size); image8.setFitHeight(size);
										buttons[x][y+1].setGraphic(image8);
									}
									buttons[x][y+1].flagstate = 2;
								}
							}
							
							if (x < 7) {
								if (y > 0) {
									if (buttons[x+1][y-1].flagstate == 0) {
										if (tiles[x+1][y-1] == 0) {
											image0 = new ImageView(new Image("file:res/0.png"));
											image0.setFitWidth(size); image0.setFitHeight(size);
											buttons[x+1][y-1].setGraphic(image0);
											if (x+1 < 7) {
												if (y-1 > 0) {
													if (buttons[x+1][y-2].flagstate == 0) {
														if (tiles[x+1][y-2] == 0) {
															image0 = new ImageView(new Image("file:res/0.png"));
															image0.setFitWidth(size); image0.setFitHeight(size);
															buttons[x+1][y-2].setGraphic(image0);
														}
														if (tiles[x+1][y-2] == 1) {
															image1 = new ImageView(new Image("file:res/1.png"));
															image1.setFitWidth(size); image1.setFitHeight(size);
															buttons[x+1][y-2].setGraphic(image1);
														}
														if (tiles[x+1][y-2] == 2) {
															image2 = new ImageView(new Image("file:res/2.png"));
															image2.setFitWidth(size); image2.setFitHeight(size);
															buttons[x+1][y-2].setGraphic(image2);
														}
														if (tiles[x+1][y-2] == 3) {
															image3 = new ImageView(new Image("file:res/3.png"));
															image3.setFitWidth(size); image3.setFitHeight(size);
															buttons[x+1][y-2].setGraphic(image3);
														}
														if (tiles[x+1][y-2] == 4) {
															image4 = new ImageView(new Image("file:res/4.png"));
															image4.setFitWidth(size); image4.setFitHeight(size);
															buttons[x+1][y-2].setGraphic(image4);
														}
														if (tiles[x+1][y-2] == 5) {
															image5 = new ImageView(new Image("file:res/5.png"));
															image5.setFitWidth(size); image5.setFitHeight(size);
															buttons[x+1][y-2].setGraphic(image5);
														}
														if (tiles[x+1][y-2] == 6) {
															image6 = new ImageView(new Image("file:res/6.png"));
															image6.setFitWidth(size); image6.setFitHeight(size);
															buttons[x+1][y-2].setGraphic(image6);
														}
														if (tiles[x+1][y-2] == 7) {
															image7 = new ImageView(new Image("file:res/7.png"));
															image7.setFitWidth(size); image7.setFitHeight(size);
															buttons[x+1][y-2].setGraphic(image7);
														}
														if (tiles[x+1][y-2] == 8) {
															image8 = new ImageView(new Image("file:res/8.png"));
															image8.setFitWidth(size); image8.setFitHeight(size);
															buttons[x-1][y-2].setGraphic(image8);
														}
														buttons[x+1][y-2].flagstate = 2;
													}
												
													if (buttons[x+2][y-2].flagstate == 0) {
														if (tiles[x+2][y-2] == 0) {
															image0 = new ImageView(new Image("file:res/0.png"));
															image0.setFitWidth(size); image0.setFitHeight(size);
															buttons[x+2][y-2].setGraphic(image0);
														}
														if (tiles[x+2][y-2] == 1) {
															image1 = new ImageView(new Image("file:res/1.png"));
															image1.setFitWidth(size); image1.setFitHeight(size);
															buttons[x+2][y-2].setGraphic(image1);
														}
														if (tiles[x+2][y-2] == 2) {
															image2 = new ImageView(new Image("file:res/2.png"));
															image2.setFitWidth(size); image2.setFitHeight(size);
															buttons[x+2][y-2].setGraphic(image2);
														}
														if (tiles[x+2][y-2] == 3) {
															image3 = new ImageView(new Image("file:res/3.png"));
															image3.setFitWidth(size); image3.setFitHeight(size);
															buttons[x+2][y-2].setGraphic(image3);
														}
														if (tiles[x+2][y-2] == 4) {
															image4 = new ImageView(new Image("file:res/4.png"));
															image4.setFitWidth(size); image4.setFitHeight(size);
															buttons[x+2][y-2].setGraphic(image4);
														}
														if (tiles[x+2][y-2] == 5) {
															image5 = new ImageView(new Image("file:res/5.png"));
															image5.setFitWidth(size); image5.setFitHeight(size);
															buttons[x+2][y-2].setGraphic(image5);
														}
														if (tiles[x+2][y-2] == 6) {
															image6 = new ImageView(new Image("file:res/6.png"));
															image6.setFitWidth(size); image6.setFitHeight(size);
															buttons[x+2][y-2].setGraphic(image6);
														}
														if (tiles[x+2][y-2] == 7) {
															image7 = new ImageView(new Image("file:res/7.png"));
															image7.setFitWidth(size); image7.setFitHeight(size);
															buttons[x+2][y-2].setGraphic(image7);
														}
														if (tiles[x+2][y-2] == 8) {
															image8 = new ImageView(new Image("file:res/8.png"));
															image8.setFitWidth(size); image8.setFitHeight(size);
															buttons[x+2][y-2].setGraphic(image8);
														}
														buttons[x+2][y-2].flagstate = 2;
													}
												}
												
												if (buttons[x+2][y-1].flagstate == 0) {
													if (tiles[x+2][y-1] == 0) {
														image0 = new ImageView(new Image("file:res/0.png"));
														image0.setFitWidth(size); image0.setFitHeight(size);
														buttons[x+2][y-1].setGraphic(image0);
													}
													if (tiles[x+2][y-1] == 1) {
														image1 = new ImageView(new Image("file:res/1.png"));
														image1.setFitWidth(size); image1.setFitHeight(size);
														buttons[x+2][y-1].setGraphic(image1);
													}
													if (tiles[x+2][y-1] == 2) {
														image2 = new ImageView(new Image("file:res/2.png"));
														image2.setFitWidth(size); image2.setFitHeight(size);
														buttons[x+2][y-1].setGraphic(image2);
													}
													if (tiles[x+2][y-1] == 3) {
														image3 = new ImageView(new Image("file:res/3.png"));
														image3.setFitWidth(size); image3.setFitHeight(size);
														buttons[x+2][y-1].setGraphic(image3);
													}
													if (tiles[x+2][y-1] == 4) {
														image4 = new ImageView(new Image("file:res/4.png"));
														image4.setFitWidth(size); image4.setFitHeight(size);
														buttons[x+2][y-1].setGraphic(image4);
													}
													if (tiles[x+2][y-1] == 5) {
														image5 = new ImageView(new Image("file:res/5.png"));
														image5.setFitWidth(size); image5.setFitHeight(size);
														buttons[x+2][y-1].setGraphic(image5);
													}
													if (tiles[x+2][y-1] == 6) {
														image6 = new ImageView(new Image("file:res/6.png"));
														image6.setFitWidth(size); image6.setFitHeight(size);
														buttons[x+2][y-1].setGraphic(image6);
													}
													if (tiles[x+2][y-1] == 7) {
														image7 = new ImageView(new Image("file:res/7.png"));
														image7.setFitWidth(size); image7.setFitHeight(size);
														buttons[x+2][y-1].setGraphic(image7);
													}
													if (tiles[x+2][y-1] == 8) {
														image8 = new ImageView(new Image("file:res/8.png"));
														image8.setFitWidth(size); image8.setFitHeight(size);
														buttons[x+2][y-1].setGraphic(image8);
													}
													buttons[x+2][y-1].flagstate = 2;
												}
												
												if (buttons[x+2][y].flagstate == 0) {
													if (tiles[x+2][y] == 0) {
														image0 = new ImageView(new Image("file:res/0.png"));
														image0.setFitWidth(size); image0.setFitHeight(size);
														buttons[x+2][y].setGraphic(image0);
													}
													if (tiles[x+2][y] == 1) {
														image1 = new ImageView(new Image("file:res/1.png"));
														image1.setFitWidth(size); image1.setFitHeight(size);
														buttons[x+2][y].setGraphic(image1);
													}
													if (tiles[x+2][y] == 2) {
														image2 = new ImageView(new Image("file:res/2.png"));
														image2.setFitWidth(size); image2.setFitHeight(size);
														buttons[x+2][y].setGraphic(image2);
													}
													if (tiles[x+2][y] == 3) {
														image3 = new ImageView(new Image("file:res/3.png"));
														image3.setFitWidth(size); image3.setFitHeight(size);
														buttons[x+2][y].setGraphic(image3);
													}
													if (tiles[x+2][y] == 4) {
														image4 = new ImageView(new Image("file:res/4.png"));
														image4.setFitWidth(size); image4.setFitHeight(size);
														buttons[x+2][y].setGraphic(image4);
													}
													if (tiles[x+2][y] == 5) {
														image5 = new ImageView(new Image("file:res/5.png"));
														image5.setFitWidth(size); image5.setFitHeight(size);
														buttons[x+2][y].setGraphic(image5);
													}
													if (tiles[x+2][y] == 6) {
														image6 = new ImageView(new Image("file:res/6.png"));
														image6.setFitWidth(size); image6.setFitHeight(size);
														buttons[x+2][y].setGraphic(image6);
													}
													if (tiles[x+2][y] == 7) {
														image7 = new ImageView(new Image("file:res/7.png"));
														image7.setFitWidth(size); image7.setFitHeight(size);
														buttons[x+2][y].setGraphic(image7);
													}
													if (tiles[x+2][y] == 8) {
														image8 = new ImageView(new Image("file:res/8.png"));
														image8.setFitWidth(size); image8.setFitHeight(size);
														buttons[x+2][y].setGraphic(image8);
													}
													buttons[x+2][y].flagstate = 2;
												}
											}
										}
										if (tiles[x+1][y-1] == 1) {
											image1 = new ImageView(new Image("file:res/1.png"));
											image1.setFitWidth(size); image1.setFitHeight(size);
											buttons[x+1][y-1].setGraphic(image1);
										}
										if (tiles[x+1][y-1] == 2) {
											image2 = new ImageView(new Image("file:res/2.png"));
											image2.setFitWidth(size); image2.setFitHeight(size);
											buttons[x+1][y-1].setGraphic(image2);
										}
										if (tiles[x+1][y-1] == 3) {
											image3 = new ImageView(new Image("file:res/3.png"));
											image3.setFitWidth(size); image3.setFitHeight(size);
											buttons[x+1][y-1].setGraphic(image3);
										}
										if (tiles[x+1][y-1] == 4) {
											image4 = new ImageView(new Image("file:res/4.png"));
											image4.setFitWidth(size); image4.setFitHeight(size);
											buttons[x+1][y-1].setGraphic(image4);
										}
										if (tiles[x+1][y-1] == 5) {
											image5 = new ImageView(new Image("file:res/5.png"));
											image5.setFitWidth(size); image5.setFitHeight(size);
											buttons[x+1][y-1].setGraphic(image5);
										}
										if (tiles[x+1][y-1] == 6) {
											image6 = new ImageView(new Image("file:res/6.png"));
											image6.setFitWidth(size); image6.setFitHeight(size);
											buttons[x+1][y-1].setGraphic(image6);
										}
										if (tiles[x+1][y-1] == 7) {
											image7 = new ImageView(new Image("file:res/7.png"));
											image7.setFitWidth(size); image7.setFitHeight(size);
											buttons[x+1][y-1].setGraphic(image7);
										}
										if (tiles[x+1][y-1] == 8) {
											image8 = new ImageView(new Image("file:res/8.png"));
											image8.setFitWidth(size); image8.setFitHeight(size);
											buttons[x+1][y-1].setGraphic(image8);
										}
										buttons[x+1][y-1].flagstate = 2;
									}
								}	
								
								if (buttons[x+1][y].flagstate == 0) {
									if (tiles[x+1][y] == 0) {
										image0 = new ImageView(new Image("file:res/0.png"));
										image0.setFitWidth(size); image0.setFitHeight(size);
										buttons[x+1][y].setGraphic(image0);
										if (x+1 < 7) {
											if (buttons[x+2][y].flagstate == 0) {
												if (tiles[x+2][y] == 0) {
													image0 = new ImageView(new Image("file:res/0.png"));
													image0.setFitWidth(size); image0.setFitHeight(size);
													buttons[x+2][y].setGraphic(image0);
												}
												if (tiles[x+2][y] == 1) {
													image1 = new ImageView(new Image("file:res/1.png"));
													image1.setFitWidth(size); image1.setFitHeight(size);
													buttons[x+2][y].setGraphic(image1);
												}
												if (tiles[x+2][y] == 2) {
													image2 = new ImageView(new Image("file:res/2.png"));
													image2.setFitWidth(size); image2.setFitHeight(size);
													buttons[x+2][y].setGraphic(image2);
												}
												if (tiles[x+2][y] == 3) {
													image3 = new ImageView(new Image("file:res/3.png"));
													image3.setFitWidth(size); image3.setFitHeight(size);
													buttons[x+2][y].setGraphic(image3);
												}
												if (tiles[x+2][y] == 4) {
													image4 = new ImageView(new Image("file:res/4.png"));
													image4.setFitWidth(size); image4.setFitHeight(size);
													buttons[x+2][y].setGraphic(image4);
												}
												if (tiles[x+2][y] == 5) {
													image5 = new ImageView(new Image("file:res/5.png"));
													image5.setFitWidth(size); image5.setFitHeight(size);
													buttons[x+2][y].setGraphic(image5);
												}
												if (tiles[x+2][y] == 6) {
													image6 = new ImageView(new Image("file:res/6.png"));
													image6.setFitWidth(size); image6.setFitHeight(size);
													buttons[x+2][y].setGraphic(image6);
												}
												if (tiles[x+2][y] == 7) {
													image7 = new ImageView(new Image("file:res/7.png"));
													image7.setFitWidth(size); image7.setFitHeight(size);
													buttons[x+2][y].setGraphic(image7);
												}
												if (tiles[x+2][y] == 8) {
													image8 = new ImageView(new Image("file:res/8.png"));
													image8.setFitWidth(size); image8.setFitHeight(size);
													buttons[x+2][y].setGraphic(image8);
												}
												buttons[x+2][y].flagstate = 2;
											}
										}
									}
									if (tiles[x+1][y] == 1) {
										image1 = new ImageView(new Image("file:res/1.png"));
										image1.setFitWidth(size); image1.setFitHeight(size);
										buttons[x+1][y].setGraphic(image1);
									}
									if (tiles[x+1][y] == 2) {
										image2 = new ImageView(new Image("file:res/2.png"));
										image2.setFitWidth(size); image2.setFitHeight(size);
										buttons[x+1][y].setGraphic(image2);
									}
									if (tiles[x+1][y] == 3) {
										image3 = new ImageView(new Image("file:res/3.png"));
										image3.setFitWidth(size); image3.setFitHeight(size);
										buttons[x+1][y].setGraphic(image3);
									}
									if (tiles[x+1][y] == 4) {
										image4 = new ImageView(new Image("file:res/4.png"));
										image4.setFitWidth(size); image4.setFitHeight(size);
										buttons[x+1][y].setGraphic(image4);
									}
									if (tiles[x+1][y] == 5) {
										image5 = new ImageView(new Image("file:res/5.png"));
										image5.setFitWidth(size); image5.setFitHeight(size);
										buttons[x+1][y].setGraphic(image5);
									}
									if (tiles[x+1][y] == 6) {
										image6 = new ImageView(new Image("file:res/6.png"));
										image6.setFitWidth(size); image6.setFitHeight(size);
										buttons[x+1][y].setGraphic(image6);
									}
									if (tiles[x+1][y] == 7) {
										image7 = new ImageView(new Image("file:res/7.png"));
										image7.setFitWidth(size); image7.setFitHeight(size);
										buttons[x+1][y].setGraphic(image7);
									}
									if (tiles[x+1][y] == 8) {
										image8 = new ImageView(new Image("file:res/8.png"));
										image8.setFitWidth(size); image8.setFitHeight(size);
										buttons[x+1][y-1].setGraphic(image8);
									}
									buttons[x+1][y].flagstate = 2;
								}
								
								if (y < 7) {
									if (buttons[x+1][y+1].flagstate == 0) {
										if (tiles[x+1][y+1] == 0) {
											image0 = new ImageView(new Image("file:res/0.png"));
											image0.setFitWidth(size); image0.setFitHeight(size);
											buttons[x+1][y+1].setGraphic(image0);
											if (x+1 < 7) {
												if (y+1 < 7) {
													if (buttons[x+2][y+1].flagstate == 0) {
														if (tiles[x+2][y+1] == 0) {
															image0 = new ImageView(new Image("file:res/0.png"));
															image0.setFitWidth(size); image0.setFitHeight(size);
															buttons[x+2][y+1].setGraphic(image0);
														}
														if (tiles[x+2][y+1] == 1) {
															image1 = new ImageView(new Image("file:res/1.png"));
															image1.setFitWidth(size); image1.setFitHeight(size);
															buttons[x+2][y+1].setGraphic(image1);
														}
														if (tiles[x+2][y+1] == 2) {
															image2 = new ImageView(new Image("file:res/2.png"));
															image2.setFitWidth(size); image2.setFitHeight(size);
															buttons[x+2][y+1].setGraphic(image2);
														}
														if (tiles[x+2][y+1] == 3) {
															image3 = new ImageView(new Image("file:res/3.png"));
															image3.setFitWidth(size); image3.setFitHeight(size);
															buttons[x+2][y+1].setGraphic(image3);
														}
														if (tiles[x+2][y+1] == 4) {
															image4 = new ImageView(new Image("file:res/4.png"));
															image4.setFitWidth(size); image4.setFitHeight(size);
															buttons[x+2][y+1].setGraphic(image4);
														}
														if (tiles[x+2][y+1] == 5) {
															image5 = new ImageView(new Image("file:res/5.png"));
															image5.setFitWidth(size); image5.setFitHeight(size);
															buttons[x+2][y+1].setGraphic(image5);
														}
														if (tiles[x+2][y+1] == 6) {
															image6 = new ImageView(new Image("file:res/6.png"));
															image6.setFitWidth(size); image6.setFitHeight(size);
															buttons[x+2][y+1].setGraphic(image6);
														}
														if (tiles[x+2][y+1] == 7) {
															image7 = new ImageView(new Image("file:res/7.png"));
															image7.setFitWidth(size); image7.setFitHeight(size);
															buttons[x+2][y+1].setGraphic(image7);
														}
														if (tiles[x+2][y+1] == 8) {
															image8 = new ImageView(new Image("file:res/8.png"));
															image8.setFitWidth(size); image8.setFitHeight(size);
															buttons[x+2][y+1].setGraphic(image8);
														}
													buttons[x+2][y+1].flagstate = 2;
												}
											}
											if (x+1 < 7 & y+1 < 7) {	
												if (buttons[x+2][y+2].flagstate == 0) {
													if (tiles[x+2][y+2] == 0) {
														image0 = new ImageView(new Image("file:res/0.png"));
														image0.setFitWidth(size); image0.setFitHeight(size);
														buttons[x+2][y+2].setGraphic(image0);
													}
													if (tiles[x+2][y+2] == 1) {
														image1 = new ImageView(new Image("file:res/1.png"));
														image1.setFitWidth(size); image1.setFitHeight(size);
														buttons[x+2][y+2].setGraphic(image1);
													}
													if (tiles[x+2][y+2] == 2) {
														image2 = new ImageView(new Image("file:res/2.png"));
														image2.setFitWidth(size); image2.setFitHeight(size);
														buttons[x+2][y+2].setGraphic(image2);
													}
													if (tiles[x+2][y+2] == 3) {
														image3 = new ImageView(new Image("file:res/3.png"));
														image3.setFitWidth(size); image3.setFitHeight(size);
														buttons[x+2][y+2].setGraphic(image3);
													}
													if (tiles[x+2][y+2] == 4) {
														image4 = new ImageView(new Image("file:res/4.png"));
														image4.setFitWidth(size); image4.setFitHeight(size);
														buttons[x+2][y+2].setGraphic(image4);
													}
													if (tiles[x+2][y+2] == 5) {
														image5 = new ImageView(new Image("file:res/5.png"));
														image5.setFitWidth(size); image5.setFitHeight(size);
														buttons[x+2][y+2].setGraphic(image5);
													}
													if (tiles[x+2][y+2] == 6) {
														image6 = new ImageView(new Image("file:res/6.png"));
														image6.setFitWidth(size); image6.setFitHeight(size);
														buttons[x+2][y+2].setGraphic(image6);
													}
													if (tiles[x+2][y+2] == 7) {
														image7 = new ImageView(new Image("file:res/7.png"));
														image7.setFitWidth(size); image7.setFitHeight(size);
														buttons[x+2][y+2].setGraphic(image7);
													}
													if (tiles[x+2][y+2] == 8) {
														image8 = new ImageView(new Image("file:res/8.png"));
														image8.setFitWidth(size); image8.setFitHeight(size);
														buttons[x+2][y+2].setGraphic(image8);
													}
													buttons[x+2][y+2].flagstate = 2;
												}
											}
										}	
											
											if (x < 7) {
												if (y+1 < 7) {
													if (buttons[x+1][y+2].flagstate == 0) {
														if (tiles[x+1][y+2] == 0) {
															image0 = new ImageView(new Image("file:res/0.png"));
															image0.setFitWidth(size); image0.setFitHeight(size);
															buttons[x+1][y+2].setGraphic(image0);
														}
														if (tiles[x+1][y+2] == 1) {
															image1 = new ImageView(new Image("file:res/1.png"));
															image1.setFitWidth(size); image1.setFitHeight(size);
															buttons[x+1][y+2].setGraphic(image1);
														}
														if (tiles[x+1][y+2] == 2) {
															image2 = new ImageView(new Image("file:res/2.png"));
															image2.setFitWidth(size); image2.setFitHeight(size);
															buttons[x+1][y+2].setGraphic(image2);
														}
														if (tiles[x+1][y+2] == 3) {
															image3 = new ImageView(new Image("file:res/3.png"));
															image3.setFitWidth(size); image3.setFitHeight(size);
															buttons[x+1][y+2].setGraphic(image3);
														}
														if (tiles[x+1][y+2] == 4) {
															image4 = new ImageView(new Image("file:res/4.png"));
															image4.setFitWidth(size); image4.setFitHeight(size);
															buttons[x+1][y+2].setGraphic(image4);
														}
														if (tiles[x+1][y+2] == 5) {
															image5 = new ImageView(new Image("file:res/5.png"));
															image5.setFitWidth(size); image5.setFitHeight(size);
															buttons[x+1][y+2].setGraphic(image5);
														}
														if (tiles[x+1][y+2] == 6) {
															image6 = new ImageView(new Image("file:res/6.png"));
															image6.setFitWidth(size); image6.setFitHeight(size);
															buttons[x+1][y+2].setGraphic(image6);
														}
														if (tiles[x+1][y+2] == 7) {
															image7 = new ImageView(new Image("file:res/7.png"));
															image7.setFitWidth(size); image7.setFitHeight(size);
															buttons[x+1][y+2].setGraphic(image7);
														}
														if (tiles[x+1][y+2] == 8) {
															image8 = new ImageView(new Image("file:res/8.png"));
															image8.setFitWidth(size); image8.setFitHeight(size);
															buttons[x+1][y+2].setGraphic(image8);
														}
														buttons[x+1][y+2].flagstate = 2;
													}
												}
											}
										}
										if (tiles[x+1][y+1] == 1) {
											image1 = new ImageView(new Image("file:res/1.png"));
											image1.setFitWidth(size); image1.setFitHeight(size);
											buttons[x+1][y+1].setGraphic(image1);
										}
										if (tiles[x+1][y+1] == 2) {
											image2 = new ImageView(new Image("file:res/2.png"));
											image2.setFitWidth(size); image2.setFitHeight(size);
											buttons[x+1][y+1].setGraphic(image2);
										}
										if (tiles[x+1][y+1] == 3) {
											image3 = new ImageView(new Image("file:res/3.png"));
											image3.setFitWidth(size); image3.setFitHeight(size);
											buttons[x+1][y+1].setGraphic(image3);
										}
										if (tiles[x+1][y+1] == 4) {
											image4 = new ImageView(new Image("file:res/4.png"));
											image4.setFitWidth(size); image4.setFitHeight(size);
											buttons[x+1][y+1].setGraphic(image4);
										}
										if (tiles[x+1][y+1] == 5) {
											image5 = new ImageView(new Image("file:res/5.png"));
											image5.setFitWidth(size); image5.setFitHeight(size);
											buttons[x+1][y+1].setGraphic(image5);
										}
										if (tiles[x+1][y+1] == 6) {
											image6 = new ImageView(new Image("file:res/6.png"));
											image6.setFitWidth(size); image6.setFitHeight(size);
											buttons[x+1][y+1].setGraphic(image6);
										}
										if (tiles[x+1][y+1] == 7) {
											image7 = new ImageView(new Image("file:res/7.png"));
											image7.setFitWidth(size); image7.setFitHeight(size);
											buttons[x+1][y+1].setGraphic(image7);
										}
										if (tiles[x+1][y+1] == 8) {
											image8 = new ImageView(new Image("file:res/8.png"));
											image8.setFitWidth(size); image8.setFitHeight(size);
											buttons[x+1][y+1].setGraphic(image8);
										}
										buttons[x+1][y+1].flagstate = 2;
									}
								}
							}
							
							if (buttons[x][y].getGraphic() != coverloop & buttons[x][y].flagstate == 2) {
								if (x > 0) {
									if (y > 0) {
										if (buttons[x-1][y-1].flagstate == 0) {
											if (tiles[x-1][y-1] == 0) {
												image0 = new ImageView(new Image("file:res/0.png"));
												image0.setFitWidth(size); image0.setFitHeight(size);
												buttons[x-1][y-1].setGraphic(image0);
											}
											if (tiles[x-1][y-1] == 1) {
												image1 = new ImageView(new Image("file:res/1.png"));
												image1.setFitWidth(size); image1.setFitHeight(size);
												buttons[x-1][y-1].setGraphic(image1);
											}
											if (tiles[x-1][y-1] == 2) {
												image2 = new ImageView(new Image("file:res/2.png"));
												image2.setFitWidth(size); image2.setFitHeight(size);
												buttons[x-1][y-1].setGraphic(image2);
											}
											if (tiles[x-1][y-1] == 3) {
												image3 = new ImageView(new Image("file:res/3.png"));
												image3.setFitWidth(size); image3.setFitHeight(size);
												buttons[x-1][y-1].setGraphic(image3);
											}
											if (tiles[x-1][y-1] == 4) {
												image4 = new ImageView(new Image("file:res/4.png"));
												image4.setFitWidth(size); image4.setFitHeight(size);
												buttons[x-1][y-1].setGraphic(image4);
											}
											if (tiles[x-1][y-1] == 5) {
												image5 = new ImageView(new Image("file:res/5.png"));
												image5.setFitWidth(size); image5.setFitHeight(size);
												buttons[x-1][y-1].setGraphic(image5);
											}
											if (tiles[x-1][y-1] == 6) {
												image6 = new ImageView(new Image("file:res/6.png"));
												image6.setFitWidth(size); image6.setFitHeight(size);
												buttons[x-1][y-1].setGraphic(image6);
											}
											if (tiles[x-1][y-1] == 7) {
												image7 = new ImageView(new Image("file:res/7.png"));
												image7.setFitWidth(size); image7.setFitHeight(size);
												buttons[x-1][y-1].setGraphic(image7);
											}
											if (tiles[x-1][y-1] == 8) {
												image8 = new ImageView(new Image("file:res/8.png"));
												image8.setFitWidth(size); image8.setFitHeight(size);
												buttons[x-1][y-1].setGraphic(image8);
											}
											if (tiles[x-1][y-1] == 9) {
												redmine = new ImageView(new Image("file:res/mine-red.png"));
												redmine.setFitWidth(size); redmine.setFitHeight(size);
												buttons[x-1][y-1].setGraphic(redmine);
												status.getChildren().remove(1);
												status.getChildren().add(1, deadButton);
												for (int k = 0; k < 8; k++) {
													for (int l = 0; l < 8; l++) {
														if (tiles[k][l] == 9 & k != x-1 & l != y-1 & buttons[k][l].getGraphic() != redmine) {
															ImageView mines = new ImageView(new Image("file:res/mine-grey.png"));
															mines.setFitWidth(33);
															mines.setFitHeight(33);
															buttons[k][l].setGraphic(mines);
														}
														buttons[k][l].setMouseTransparent(true);
													}
												}
												gameOver();
											}
											buttons[x-1][y-1].flagstate = 2;
										}
									}
									
									if (buttons[x-1][y].flagstate == 0) {
										if (tiles[x-1][y] == 0) {
											image0 = new ImageView(new Image("file:res/0.png"));
											image0.setFitWidth(size); image0.setFitHeight(size);
											buttons[x-1][y].setGraphic(image0);
										}
										if (tiles[x-1][y] == 1) {
											image1 = new ImageView(new Image("file:res/1.png"));
											image1.setFitWidth(size); image1.setFitHeight(size);
											buttons[x-1][y].setGraphic(image1);
										}
										if (tiles[x-1][y] == 2) {
											image2 = new ImageView(new Image("file:res/2.png"));
											image2.setFitWidth(size); image2.setFitHeight(size);
											buttons[x-1][y].setGraphic(image2);
										}
										if (tiles[x-1][y] == 3) {
											image3 = new ImageView(new Image("file:res/3.png"));
											image3.setFitWidth(size); image3.setFitHeight(size);
											buttons[x-1][y].setGraphic(image3);
										}
										if (tiles[x-1][y] == 4) {
											image4 = new ImageView(new Image("file:res/4.png"));
											image4.setFitWidth(size); image4.setFitHeight(size);
											buttons[x-1][y].setGraphic(image4);
										}
										if (tiles[x-1][y] == 5) {
											image5 = new ImageView(new Image("file:res/5.png"));
											image5.setFitWidth(size); image5.setFitHeight(size);
											buttons[x-1][y].setGraphic(image5);
										}
										if (tiles[x-1][y] == 6) {
											image6 = new ImageView(new Image("file:res/6.png"));
											image6.setFitWidth(size); image6.setFitHeight(size);
											buttons[x-1][y].setGraphic(image6);
										}
										if (tiles[x-1][y] == 7) {
											image7 = new ImageView(new Image("file:res/7.png"));
											image7.setFitWidth(size); image7.setFitHeight(size);
											buttons[x-1][y].setGraphic(image7);
										}
										if (tiles[x-1][y] == 8) {
											image8 = new ImageView(new Image("file:res/8.png"));
											image8.setFitWidth(size); image8.setFitHeight(size);
											buttons[x-1][y].setGraphic(image8);
										}
										if (tiles[x-1][y] == 9) {
											redmine = new ImageView(new Image("file:res/mine-red.png"));
											redmine.setFitWidth(size); redmine.setFitHeight(size);
											buttons[x-1][y].setGraphic(redmine);
											status.getChildren().remove(1);
											status.getChildren().add(1, deadButton);
											for (int k = 0; k < 8; k++) {
												for (int l = 0; l < 8; l++) {
													if (tiles[k][l] == 9 & k != x-1 & l != y & buttons[k][l].getGraphic() != redmine) {
														ImageView mines = new ImageView(new Image("file:res/mine-grey.png"));
														mines.setFitWidth(33);
														mines.setFitHeight(33);
														buttons[k][l].setGraphic(mines);
													}
													buttons[k][l].setMouseTransparent(true);
												}
											}
											gameOver();
										}
										buttons[x-1][y].flagstate = 2;
									}
									
									if (y < 7) {
										if (buttons[x-1][y+1].flagstate == 0) {
											if (tiles[x-1][y+1] == 0) {
												image0 = new ImageView(new Image("file:res/0.png"));
												image0.setFitWidth(size); image0.setFitHeight(size);
												buttons[x-1][y+1].setGraphic(image0);
											}
											if (tiles[x-1][y+1] == 1) {
												image1 = new ImageView(new Image("file:res/1.png"));
												image1.setFitWidth(size); image1.setFitHeight(size);
												buttons[x-1][y+1].setGraphic(image1);
											}
											if (tiles[x-1][y+1] == 2) {
												image2 = new ImageView(new Image("file:res/2.png"));
												image2.setFitWidth(size); image2.setFitHeight(size);
												buttons[x-1][y+1].setGraphic(image2);
											}
											if (tiles[x-1][y+1] == 3) {
												image3 = new ImageView(new Image("file:res/3.png"));
												image3.setFitWidth(size); image3.setFitHeight(size);
												buttons[x-1][y+1].setGraphic(image3);
											}
											if (tiles[x-1][y+1] == 4) {
												image4 = new ImageView(new Image("file:res/4.png"));
												image4.setFitWidth(size); image4.setFitHeight(size);
												buttons[x-1][y+1].setGraphic(image4);
											}
											if (tiles[x-1][y+1] == 5) {
												image5 = new ImageView(new Image("file:res/5.png"));
												image5.setFitWidth(size); image5.setFitHeight(size);
												buttons[x-1][y+1].setGraphic(image5);
											}
											if (tiles[x-1][y+1] == 6) {
												image6 = new ImageView(new Image("file:res/6.png"));
												image6.setFitWidth(size); image6.setFitHeight(size);
												buttons[x-1][y+1].setGraphic(image6);
											}
											if (tiles[x-1][y+1] == 7) {
												image7 = new ImageView(new Image("file:res/7.png"));
												image7.setFitWidth(size); image7.setFitHeight(size);
												buttons[x-1][y+1].setGraphic(image7);
											}
											if (tiles[x-1][y+1] == 8) {
												image8 = new ImageView(new Image("file:res/8.png"));
												image8.setFitWidth(size); image8.setFitHeight(size);
												buttons[x-1][y+1].setGraphic(image8);
											}
											if (tiles[x-1][y+1] == 9) {
												redmine = new ImageView(new Image("file:res/mine-red.png"));
												redmine.setFitWidth(size); redmine.setFitHeight(size);
												buttons[x-1][y+1].setGraphic(redmine);
												status.getChildren().remove(1);
												status.getChildren().add(1, deadButton);
												for (int k = 0; k < 8; k++) {
													for (int l = 0; l < 8; l++) {
														if (tiles[k][l] == 9 & k != x-1 & l != y+1 & buttons[k][l].getGraphic() != redmine) {
															ImageView mines = new ImageView(new Image("file:res/mine-grey.png"));
															mines.setFitWidth(33);
															mines.setFitHeight(33);
															buttons[k][l].setGraphic(mines);
														}
														buttons[k][l].setMouseTransparent(true);
													}
												}
												gameOver();
											}
											buttons[x-1][y+1].flagstate = 2;
										}
									}
								}
								
								if (y > 0) {
									if (buttons[x][y-1].flagstate == 0) {
										if (tiles[x][y-1] == 0) {
											image0 = new ImageView(new Image("file:res/0.png"));
											image0.setFitWidth(size); image0.setFitHeight(size);
											buttons[x][y-1].setGraphic(image0);
										}
										if (tiles[x][y-1] == 1) {
											image1 = new ImageView(new Image("file:res/1.png"));
											image1.setFitWidth(size); image1.setFitHeight(size);
											buttons[x][y-1].setGraphic(image1);
										}
										if (tiles[x][y-1] == 2) {
											image2 = new ImageView(new Image("file:res/2.png"));
											image2.setFitWidth(size); image2.setFitHeight(size);
											buttons[x][y-1].setGraphic(image2);
										}
										if (tiles[x][y-1] == 3) {
											image3 = new ImageView(new Image("file:res/3.png"));
											image3.setFitWidth(size); image3.setFitHeight(size);
											buttons[x][y-1].setGraphic(image3);
										}
										if (tiles[x][y-1] == 4) {
											image4 = new ImageView(new Image("file:res/4.png"));
											image4.setFitWidth(size); image4.setFitHeight(size);
											buttons[x][y-1].setGraphic(image4);
										}
										if (tiles[x][y-1] == 5) {
											image5 = new ImageView(new Image("file:res/5.png"));
											image5.setFitWidth(size); image5.setFitHeight(size);
											buttons[x][y-1].setGraphic(image5);
										}
										if (tiles[x][y-1] == 6) {
											image6 = new ImageView(new Image("file:res/6.png"));
											image6.setFitWidth(size); image6.setFitHeight(size);
											buttons[x][y-1].setGraphic(image6);
										}
										if (tiles[x][y-1] == 7) {
											image7 = new ImageView(new Image("file:res/7.png"));
											image7.setFitWidth(size); image7.setFitHeight(size);
											buttons[x][y-1].setGraphic(image7);
										}
										if (tiles[x][y-1] == 8) {
											image8 = new ImageView(new Image("file:res/8.png"));
											image8.setFitWidth(size); image8.setFitHeight(size);
											buttons[x][y-1].setGraphic(image8);
										}
										if (tiles[x][y-1] == 9) {
											redmine = new ImageView(new Image("file:res/mine-red.png"));
											redmine.setFitWidth(size); redmine.setFitHeight(size);
											buttons[x][y-1].setGraphic(redmine);
											status.getChildren().remove(1);
											status.getChildren().add(1, deadButton);
											for (int k = 0; k < 8; k++) {
												for (int l = 0; l < 8; l++) {
													if (tiles[k][l] == 9 & k != x & l != y-1 & buttons[k][l].getGraphic() != redmine) {
														ImageView mines = new ImageView(new Image("file:res/mine-grey.png"));
														mines.setFitWidth(33);
														mines.setFitHeight(33);
														buttons[k][l].setGraphic(mines);
													}
													buttons[k][l].setMouseTransparent(true);
												}
											}
											gameOver();
										}
										buttons[x][y-1].flagstate = 2;
									}
								}
								
								if (y < 7) {
									if (buttons[x][y+1].flagstate == 0) {
										if (tiles[x][y+1] == 0) {
											image0 = new ImageView(new Image("file:res/0.png"));
											image0.setFitWidth(size); image0.setFitHeight(size);
											buttons[x][y+1].setGraphic(image0);
										}
										if (tiles[x][y+1] == 1) {
											image1 = new ImageView(new Image("file:res/1.png"));
											image1.setFitWidth(size); image1.setFitHeight(size);
											buttons[x][y+1].setGraphic(image1);
										}
										if (tiles[x][y+1] == 2) {
											image2 = new ImageView(new Image("file:res/2.png"));
											image2.setFitWidth(size); image2.setFitHeight(size);
											buttons[x][y+1].setGraphic(image2);
										}
										if (tiles[x][y+1] == 3) {
											image3 = new ImageView(new Image("file:res/3.png"));
											image3.setFitWidth(size); image3.setFitHeight(size);
											buttons[x][y+1].setGraphic(image3);
										}
										if (tiles[x][y+1] == 4) {
											image4 = new ImageView(new Image("file:res/4.png"));
											image4.setFitWidth(size); image4.setFitHeight(size);
											buttons[x][y+1].setGraphic(image4);
										}
										if (tiles[x][y+1] == 5) {
											image5 = new ImageView(new Image("file:res/5.png"));
											image5.setFitWidth(size); image5.setFitHeight(size);
											buttons[x][y+1].setGraphic(image5);
										}
										if (tiles[x][y+1] == 6) {
											image6 = new ImageView(new Image("file:res/6.png"));
											image6.setFitWidth(size); image6.setFitHeight(size);
											buttons[x][y+1].setGraphic(image6);
										}
										if (tiles[x][y+1] == 7) {
											image7 = new ImageView(new Image("file:res/7.png"));
											image7.setFitWidth(size); image7.setFitHeight(size);
											buttons[x][y+1].setGraphic(image7);
										}
										if (tiles[x][y+1] == 8) {
											image8 = new ImageView(new Image("file:res/8.png"));
											image8.setFitWidth(size); image8.setFitHeight(size);
											buttons[x][y+1].setGraphic(image8);
										}
										if (tiles[x][y+1] == 9) {
											redmine = new ImageView(new Image("file:res/mine-red.png"));
											redmine.setFitWidth(size); redmine.setFitHeight(size);
											buttons[x][y+1].setGraphic(redmine);
											status.getChildren().remove(1);
											status.getChildren().add(1, deadButton);
											for (int k = 0; k < 8; k++) {
												for (int l = 0; l < 8; l++) {
													if (tiles[k][l] == 9 & k != x & l != y+1 & buttons[k][l].getGraphic() != redmine) {
														ImageView mines = new ImageView(new Image("file:res/mine-grey.png"));
														mines.setFitWidth(33);
														mines.setFitHeight(33);
														buttons[k][l].setGraphic(mines);
													}
													buttons[k][l].setMouseTransparent(true);
												}
											}
											gameOver();
										}
										buttons[x][y+1].flagstate = 2;
									}
								}
								
								if (x < 7) {
									if (y > 0) {
										if (buttons[x+1][y-1].flagstate == 0) {
											if (tiles[x+1][y-1] == 0) {
												image0 = new ImageView(new Image("file:res/0.png"));
												image0.setFitWidth(size); image0.setFitHeight(size);
												buttons[x+1][y-1].setGraphic(image0);
											}
											if (tiles[x+1][y-1] == 1) {
												image1 = new ImageView(new Image("file:res/1.png"));
												image1.setFitWidth(size); image1.setFitHeight(size);
												buttons[x+1][y-1].setGraphic(image1);
											}
											if (tiles[x+1][y-1] == 2) {
												image2 = new ImageView(new Image("file:res/2.png"));
												image2.setFitWidth(size); image2.setFitHeight(size);
												buttons[x+1][y-1].setGraphic(image2);
											}
											if (tiles[x+1][y-1] == 3) {
												image3 = new ImageView(new Image("file:res/3.png"));
												image3.setFitWidth(size); image3.setFitHeight(size);
												buttons[x+1][y-1].setGraphic(image3);
											}
											if (tiles[x+1][y-1] == 4) {
												image4 = new ImageView(new Image("file:res/4.png"));
												image4.setFitWidth(size); image4.setFitHeight(size);
												buttons[x+1][y-1].setGraphic(image4);
											}
											if (tiles[x+1][y-1] == 5) {
												image5 = new ImageView(new Image("file:res/5.png"));
												image5.setFitWidth(size); image5.setFitHeight(size);
												buttons[x+1][y-1].setGraphic(image5);
											}
											if (tiles[x+1][y-1] == 6) {
												image6 = new ImageView(new Image("file:res/6.png"));
												image6.setFitWidth(size); image6.setFitHeight(size);
												buttons[x+1][y-1].setGraphic(image6);
											}
											if (tiles[x+1][y-1] == 7) {
												image7 = new ImageView(new Image("file:res/7.png"));
												image7.setFitWidth(size); image7.setFitHeight(size);
												buttons[x+1][y-1].setGraphic(image7);
											}
											if (tiles[x+1][y-1] == 8) {
												image8 = new ImageView(new Image("file:res/8.png"));
												image8.setFitWidth(size); image8.setFitHeight(size);
												buttons[x+1][y-1].setGraphic(image8);
											}
											if (tiles[x+1][y-1] == 9) {
												redmine = new ImageView(new Image("file:res/mine-red.png"));
												redmine.setFitWidth(size); redmine.setFitHeight(size);
												buttons[x+1][y-1].setGraphic(redmine);
												status.getChildren().remove(1);
												status.getChildren().add(1, deadButton);
												for (int k = 0; k < 8; k++) {
													for (int l = 0; l < 8; l++) {
														if (tiles[k][l] == 9 & k != x+1 & l != y-1 & buttons[k][l].getGraphic() != redmine) {
															ImageView mines = new ImageView(new Image("file:res/mine-grey.png"));
															mines.setFitWidth(33);
															mines.setFitHeight(33);
															buttons[k][l].setGraphic(mines);
														}
														buttons[k][l].setMouseTransparent(true);
													}
												}
												gameOver();
											}
											buttons[x+1][y-1].flagstate = 2;
										}
									}	
									
									if (buttons[x+1][y].flagstate == 0) {
										if (tiles[x+1][y] == 0) {
											image0 = new ImageView(new Image("file:res/0.png"));
											image0.setFitWidth(size); image0.setFitHeight(size);
											buttons[x+1][y].setGraphic(image0);
										}
										if (tiles[x+1][y] == 1) {
											image1 = new ImageView(new Image("file:res/1.png"));
											image1.setFitWidth(size); image1.setFitHeight(size);
											buttons[x+1][y].setGraphic(image1);
										}
										if (tiles[x+1][y] == 2) {
											image2 = new ImageView(new Image("file:res/2.png"));
											image2.setFitWidth(size); image2.setFitHeight(size);
											buttons[x+1][y].setGraphic(image2);
										}
										if (tiles[x+1][y] == 3) {
											image3 = new ImageView(new Image("file:res/3.png"));
											image3.setFitWidth(size); image3.setFitHeight(size);
											buttons[x+1][y].setGraphic(image3);
										}
										if (tiles[x+1][y] == 4) {
											image4 = new ImageView(new Image("file:res/4.png"));
											image4.setFitWidth(size); image4.setFitHeight(size);
											buttons[x+1][y].setGraphic(image4);
										}
										if (tiles[x+1][y] == 5) {
											image5 = new ImageView(new Image("file:res/5.png"));
											image5.setFitWidth(size); image5.setFitHeight(size);
											buttons[x+1][y].setGraphic(image5);
										}
										if (tiles[x+1][y] == 6) {
											image6 = new ImageView(new Image("file:res/6.png"));
											image6.setFitWidth(size); image6.setFitHeight(size);
											buttons[x+1][y].setGraphic(image6);
										}
										if (tiles[x+1][y] == 7) {
											image7 = new ImageView(new Image("file:res/7.png"));
											image7.setFitWidth(size); image7.setFitHeight(size);
											buttons[x+1][y].setGraphic(image7);
										}
										if (tiles[x+1][y] == 8) {
											image8 = new ImageView(new Image("file:res/8.png"));
											image8.setFitWidth(size); image8.setFitHeight(size);
											buttons[x+1][y].setGraphic(image8);
										}
										if (tiles[x+1][y] == 9) {
											redmine = new ImageView(new Image("file:res/mine-red.png"));
											redmine.setFitWidth(size); redmine.setFitHeight(size);
											buttons[x+1][y].setGraphic(redmine);
											status.getChildren().remove(1);
											status.getChildren().add(1, deadButton);
											for (int k = 0; k < 8; k++) {
												for (int l = 0; l < 8; l++) {
													if (tiles[k][l] == 9 & k != x+1 & l != y & buttons[k][l].getGraphic() != redmine) {
														ImageView mines = new ImageView(new Image("file:res/mine-grey.png"));
														mines.setFitWidth(33);
														mines.setFitHeight(33);
														buttons[k][l].setGraphic(mines);
													}
													buttons[k][l].setMouseTransparent(true);
												}
											}
											gameOver();
										}
										buttons[x+1][y].flagstate = 2;
									}
									
									if (y < 7) {
										if (buttons[x+1][y+1].flagstate == 0) {
											if (tiles[x+1][y+1] == 0) {
												image0 = new ImageView(new Image("file:res/0.png"));
												image0.setFitWidth(size); image0.setFitHeight(size);
												buttons[x+1][y+1].setGraphic(image0);
											}
											if (tiles[x+1][y+1] == 1) {
												image1 = new ImageView(new Image("file:res/1.png"));
												image1.setFitWidth(size); image1.setFitHeight(size);
												buttons[x+1][y+1].setGraphic(image1);
											}
											if (tiles[x+1][y+1] == 2) {
												image2 = new ImageView(new Image("file:res/2.png"));
												image2.setFitWidth(size); image2.setFitHeight(size);
												buttons[x+1][y+1].setGraphic(image2);
											}
											if (tiles[x+1][y+1] == 3) {
												image3 = new ImageView(new Image("file:res/3.png"));
												image3.setFitWidth(size); image3.setFitHeight(size);
												buttons[x+1][y+1].setGraphic(image3);
											}
											if (tiles[x+1][y+1] == 4) {
												image4 = new ImageView(new Image("file:res/4.png"));
												image4.setFitWidth(size); image4.setFitHeight(size);
												buttons[x+1][y+1].setGraphic(image4);
											}
											if (tiles[x+1][y+1] == 5) {
												image5 = new ImageView(new Image("file:res/5.png"));
												image5.setFitWidth(size); image5.setFitHeight(size);
												buttons[x+1][y+1].setGraphic(image5);
											}
											if (tiles[x+1][y+1] == 6) {
												image6 = new ImageView(new Image("file:res/6.png"));
												image6.setFitWidth(size); image6.setFitHeight(size);
												buttons[x+1][y+1].setGraphic(image6);
											}
											if (tiles[x+1][y+1] == 7) {
												image7 = new ImageView(new Image("file:res/7.png"));
												image7.setFitWidth(size); image7.setFitHeight(size);
												buttons[x+1][y+1].setGraphic(image7);
											}
											if (tiles[x+1][y+1] == 8) {
												image8 = new ImageView(new Image("file:res/8.png"));
												image8.setFitWidth(size); image8.setFitHeight(size);
												buttons[x+1][y+1].setGraphic(image8);
											}
											if (tiles[x+1][y+1] == 9) {
												redmine = new ImageView(new Image("file:res/mine-red.png"));
												redmine.setFitWidth(size); redmine.setFitHeight(size);
												buttons[x+1][y+1].setGraphic(redmine);
												status.getChildren().remove(1);
												status.getChildren().add(1, deadButton);
												for (int k = 0; k < 8; k++) {
													for (int l = 0; l < 8; l++) {
														if (tiles[k][l] == 9 & k != x+1 & l != y+1 & buttons[k][l].getGraphic() != redmine) {
															ImageView mines = new ImageView(new Image("file:res/mine-grey.png"));
															mines.setFitWidth(33);
															mines.setFitHeight(33);
															buttons[k][l].setGraphic(mines);
														}
														buttons[k][l].setMouseTransparent(true);
													}
												}
												gameOver();
											}
											buttons[x+1][y+1].flagstate = 2;
										}
									}
								}
							}
							for (int a = 0; a < 8; a++) {
								for (int b = 0; b < 8; b++) {
									if (tiles[a][b] != 9 & buttons[a][b].btnstate == 1 | buttons[a][b].flagstate == 2) {
										buttonsClicked.add(0);
										if (buttonsClicked.size() == 54) {
											status.getChildren().remove(1);
											status.getChildren().add(1, winButton);
											for (int i = 0; i < 8; i++) {
												for (int j = 0; j < 8; j++) {
													buttons[i][j].setMouseTransparent(true);
												}
											}
											
											win();		
										}
									}
								}
							}
							System.out.println(buttonsClicked.size());
							buttonsClicked.clear();
						}
					});
				}

				tile.add(buttons[i][j], i, j);
			}
		}
		
		buttons[0][0].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[0][0].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[0][0].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[0][1].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[0][1].flagstate == 0) {
					bombsLeft.remove(1);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[0][1].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[0][2].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[0][2].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[0][2].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[0][3].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[0][3].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[0][3].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[0][4].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[0][4].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[0][4].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[0][5].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[0][5].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[0][5].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[0][6].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[0][6].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[0][6].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[0][7].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[0][7].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[0][7].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[1][0].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[1][0].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[1][0].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[1][1].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[1][1].flagstate == 0) {
					bombsLeft.remove(1);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[1][1].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[1][2].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[1][2].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[1][2].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[1][3].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[1][3].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[1][3].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[1][4].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[1][4].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[1][4].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[1][5].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[1][5].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[1][5].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[1][6].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[1][6].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[1][6].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[1][7].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[1][7].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[1][7].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[2][0].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[2][0].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[2][0].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[2][1].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[2][1].flagstate == 0) {
					bombsLeft.remove(1);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[2][1].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[2][2].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[2][2].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[2][2].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[2][3].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[2][3].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[2][3].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[2][4].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[2][4].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[2][4].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[2][5].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[2][5].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[2][5].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[2][6].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[2][6].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[2][6].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[2][7].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[2][7].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[2][7].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});

		buttons[3][0].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[3][0].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[3][0].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[3][1].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[3][1].flagstate == 0) {
					bombsLeft.remove(1);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[3][1].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[3][2].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[3][2].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[3][2].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[3][3].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[3][3].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[3][3].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[3][4].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[3][4].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[3][4].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[3][5].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[3][5].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[3][5].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[3][6].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[3][6].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[3][6].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[3][7].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[3][7].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[3][7].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[4][0].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[3][0].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[3][0].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[4][1].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[4][1].flagstate == 0) {
					bombsLeft.remove(1);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[4][1].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[4][2].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[4][2].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[4][2].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[4][3].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[4][3].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[4][3].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[4][4].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[4][4].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[4][4].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[4][5].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[4][5].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[4][5].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[4][6].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[4][6].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[4][6].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[4][7].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[4][7].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[4][7].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});

		buttons[5][0].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[5][0].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[5][0].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[5][1].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[5][1].flagstate == 0) {
					bombsLeft.remove(1);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[5][1].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[5][2].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[5][2].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[5][2].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[5][3].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[5][3].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[5][3].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[5][4].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[5][4].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[5][4].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[5][5].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[5][5].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[5][5].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[5][6].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[5][6].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[5][6].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[5][7].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[5][7].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[5][7].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});

		buttons[6][0].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[6][0].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[6][0].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[6][1].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[6][1].flagstate == 0) {
					bombsLeft.remove(1);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[6][1].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[6][2].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[6][2].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[6][2].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[6][3].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[6][3].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[6][3].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[6][4].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[6][4].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[6][4].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[6][5].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[6][5].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[6][5].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[6][6].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[6][6].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[6][6].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[6][7].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[6][7].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[6][7].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});

		buttons[7][0].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[7][0].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[7][0].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[7][1].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[7][1].flagstate == 0) {
					bombsLeft.remove(1);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[7][1].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[7][2].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[7][2].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[7][2].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[7][3].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[7][3].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[7][3].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[7][4].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[7][4].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[7][4].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[7][5].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[7][5].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[7][5].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[7][6].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[7][6].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[7][6].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		buttons[7][7].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				MouseButton rightClick = e.getButton();
				if (rightClick == MouseButton.SECONDARY & buttons[7][7].flagstate == 0) {
					bombsLeft.remove(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
				
				else if (rightClick == MouseButton.SECONDARY & buttons[7][7].flagstate == 1) {
					bombsLeft.add(0);
					status.getChildren().remove(0);
					status.getChildren().add(0, new CustomPane("" + bombsLeft.size()));
				}
			}
		});
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(+ tiles[j][i] + " ");
			}
			
			System.out.println();
		}
		
		System.out.println();
		
		root.setTop(status);
		root.setCenter(tile);
		
		stage.setTitle("Minesweeper");
		stage.setScene(new Scene(root));
	    stage.setResizable(false);
		stage.show();
	}
	
	void restart(Stage stage) {
		startGame(stage);
	}
	
	public static void gameOver() {
		Alert gameOver = new Alert(AlertType.INFORMATION);
		gameOver.setTitle("Game Over!");
		gameOver.setHeaderText("Bomb Exploded!");
		gameOver.setContentText("You clicked on a bomb.");
		gameOver.showAndWait();
	}

	public static void win() {
		Alert win = new Alert(AlertType.CONFIRMATION);
		win.setTitle("Win!");
		win.setHeaderText("Congratulations!");
		win.setContentText("You found all the bombs.");
		win.showAndWait();
	}
}

class CustomPane extends StackPane {
	public CustomPane(String title) {
		getChildren().add(new Label(title));
		setStyle("-fx-border-color:grey");
		setPadding(new Insets(25, 25, 25, 25));
		setMinWidth(83); setMinHeight(83);
	}
}

class MineButton extends Button implements EventHandler<MouseEvent> {
	int state, flagstate, btnstate;
	ImageView image0, image1, image2, image3, image4, image5, image6, image7, image8, redmine, flag, cover;
	
	public MineButton(int state) {
		this.state = state;
		flagstate = 0;
		btnstate = 0;
		double size = 33;
		setMinWidth(size);
		setMaxWidth(size);
		setMinHeight(size);
		setMaxHeight(size);
		
		image0 = new ImageView(new Image("file:res/0.png"));
		image1 = new ImageView(new Image("file:res/1.png"));
		image2 = new ImageView(new Image("file:res/2.png"));
		image3 = new ImageView(new Image("file:res/3.png"));
		image4 = new ImageView(new Image("file:res/4.png"));
		image5 = new ImageView(new Image("file:res/5.png"));
		image6 = new ImageView(new Image("file:res/6.png"));
		image7 = new ImageView(new Image("file:res/7.png"));
		image8 = new ImageView(new Image("file:res/8.png"));
		redmine = new ImageView(new Image("file:res/mine-red.png"));
		flag = new ImageView(new Image("file:res/flag.png"));
		cover = new ImageView(new Image("file:res/cover.png"));
		
		image0.setFitWidth(size); image0.setFitHeight(size);
		image1.setFitWidth(size); image1.setFitHeight(size);
		image2.setFitWidth(size); image2.setFitHeight(size);
		image3.setFitWidth(size); image3.setFitHeight(size);
		image4.setFitWidth(size); image4.setFitHeight(size);
		image5.setFitWidth(size); image5.setFitHeight(size);
		image6.setFitWidth(size); image6.setFitHeight(size);
		image7.setFitWidth(size); image7.setFitHeight(size);
		image8.setFitWidth(size); image8.setFitHeight(size);
		redmine.setFitWidth(size); redmine.setFitHeight(size);
		flag.setFitWidth(size); flag.setFitHeight(size);
		cover.setFitWidth(size); cover.setFitHeight(size);
	}
	
	public void handle(MouseEvent e) {
		MouseButton mouseClick = e.getButton();
		
		if (btnstate == 0) {
			if (flagstate == 0) {
				if (mouseClick == MouseButton.SECONDARY) {
					setGraphic(flag);
					flagstate = 1;
				}
			}
			
			else if (flagstate == 1) {
				if (mouseClick == MouseButton.SECONDARY) {
					setGraphic(cover);
					flagstate = 0;
				}
			}	
		
			if (mouseClick == MouseButton.PRIMARY & flagstate == 0) {
				if (state == 0)
					setGraphic(image0);
				else if (state == 1)
					setGraphic(image1);
				else if (state == 2)
					setGraphic(image2);
				else if (state == 3)
					setGraphic(image3);
				else if (state == 4)
					setGraphic(image4);
				else if (state == 5)
					setGraphic(image5);
				else if (state == 6)
					setGraphic(image6);
				else if (state == 7)
					setGraphic(image7);
				else if (state == 8)
					setGraphic(image8);
				else if (state == 9) {
					setGraphic(redmine);
					Minesweeper.gameOver();
				}
				btnstate = 1;
			}
		}
		
		else if (btnstate == 1) {
			setMouseTransparent(true);
		}
	}
}