namespace travelAgency2
{
    partial class PurchaseForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.labelPurchase = new System.Windows.Forms.Label();
            this.btnBack = new System.Windows.Forms.Button();
            this.btnPurchase = new System.Windows.Forms.Button();
            this.txtBoxClientName = new System.Windows.Forms.TextBox();
            this.txtBoxClientAddress = new System.Windows.Forms.TextBox();
            this.txtBoxSeats = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.txtBoxTourist1 = new System.Windows.Forms.TextBox();
            this.txtBoxTourist2 = new System.Windows.Forms.TextBox();
            this.txtBoxTourist3 = new System.Windows.Forms.TextBox();
            this.labelError = new System.Windows.Forms.Label();
            this.labelSucces = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // labelPurchase
            // 
            this.labelPurchase.AutoSize = true;
            this.labelPurchase.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelPurchase.Location = new System.Drawing.Point(12, 45);
            this.labelPurchase.Name = "labelPurchase";
            this.labelPurchase.Size = new System.Drawing.Size(100, 37);
            this.labelPurchase.TabIndex = 0;
            this.labelPurchase.Text = "label1";
            // 
            // btnBack
            // 
            this.btnBack.Location = new System.Drawing.Point(120, 408);
            this.btnBack.Name = "btnBack";
            this.btnBack.Size = new System.Drawing.Size(75, 23);
            this.btnBack.TabIndex = 1;
            this.btnBack.Text = "Back";
            this.btnBack.UseVisualStyleBackColor = true;
            this.btnBack.Click += new System.EventHandler(this.btnBack_Click);
            // 
            // btnPurchase
            // 
            this.btnPurchase.Location = new System.Drawing.Point(360, 408);
            this.btnPurchase.Name = "btnPurchase";
            this.btnPurchase.Size = new System.Drawing.Size(95, 23);
            this.btnPurchase.TabIndex = 2;
            this.btnPurchase.Text = "Purchase";
            this.btnPurchase.UseVisualStyleBackColor = true;
            this.btnPurchase.Click += new System.EventHandler(this.btnPurchase_Click);
            // 
            // txtBoxClientName
            // 
            this.txtBoxClientName.Location = new System.Drawing.Point(335, 116);
            this.txtBoxClientName.Name = "txtBoxClientName";
            this.txtBoxClientName.Size = new System.Drawing.Size(100, 22);
            this.txtBoxClientName.TabIndex = 3;
            // 
            // txtBoxClientAddress
            // 
            this.txtBoxClientAddress.Location = new System.Drawing.Point(335, 156);
            this.txtBoxClientAddress.Name = "txtBoxClientAddress";
            this.txtBoxClientAddress.Size = new System.Drawing.Size(100, 22);
            this.txtBoxClientAddress.TabIndex = 4;
            // 
            // txtBoxSeats
            // 
            this.txtBoxSeats.Location = new System.Drawing.Point(335, 194);
            this.txtBoxSeats.Name = "txtBoxSeats";
            this.txtBoxSeats.Size = new System.Drawing.Size(100, 22);
            this.txtBoxSeats.TabIndex = 5;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(116, 116);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(99, 20);
            this.label1.TabIndex = 6;
            this.label1.Text = "Client Name:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(116, 156);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(116, 20);
            this.label2.TabIndex = 7;
            this.label2.Text = "Client Address:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(126, 196);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(55, 20);
            this.label3.TabIndex = 8;
            this.label3.Text = "Seats:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(121, 230);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(74, 20);
            this.label4.TabIndex = 10;
            this.label4.Text = "Tourist 1:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(121, 265);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(74, 20);
            this.label5.TabIndex = 11;
            this.label5.Text = "Tourist 2:";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(121, 299);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(74, 20);
            this.label6.TabIndex = 12;
            this.label6.Text = "Tourist 3:";
            // 
            // txtBoxTourist1
            // 
            this.txtBoxTourist1.Location = new System.Drawing.Point(335, 230);
            this.txtBoxTourist1.Name = "txtBoxTourist1";
            this.txtBoxTourist1.Size = new System.Drawing.Size(100, 22);
            this.txtBoxTourist1.TabIndex = 13;
            // 
            // txtBoxTourist2
            // 
            this.txtBoxTourist2.Location = new System.Drawing.Point(335, 263);
            this.txtBoxTourist2.Name = "txtBoxTourist2";
            this.txtBoxTourist2.Size = new System.Drawing.Size(100, 22);
            this.txtBoxTourist2.TabIndex = 14;
            // 
            // txtBoxTourist3
            // 
            this.txtBoxTourist3.Location = new System.Drawing.Point(335, 297);
            this.txtBoxTourist3.Name = "txtBoxTourist3";
            this.txtBoxTourist3.Size = new System.Drawing.Size(100, 22);
            this.txtBoxTourist3.TabIndex = 15;
            // 
            // labelError
            // 
            this.labelError.AutoSize = true;
            this.labelError.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelError.ForeColor = System.Drawing.Color.IndianRed;
            this.labelError.Location = new System.Drawing.Point(126, 335);
            this.labelError.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelError.Name = "labelError";
            this.labelError.Size = new System.Drawing.Size(209, 24);
            this.labelError.TabIndex = 16;
            this.labelError.Text = "Something went wrong!";
            this.labelError.Visible = false;
            // 
            // labelSucces
            // 
            this.labelSucces.AutoSize = true;
            this.labelSucces.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelSucces.ForeColor = System.Drawing.Color.Chartreuse;
            this.labelSucces.Location = new System.Drawing.Point(121, 370);
            this.labelSucces.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.labelSucces.Name = "labelSucces";
            this.labelSucces.Size = new System.Drawing.Size(265, 24);
            this.labelSucces.TabIndex = 17;
            this.labelSucces.Text = "Your purchase was successful";
            this.labelSucces.Visible = false;
            // 
            // PurchaseForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(849, 512);
            this.Controls.Add(this.labelSucces);
            this.Controls.Add(this.labelError);
            this.Controls.Add(this.txtBoxTourist3);
            this.Controls.Add(this.txtBoxTourist2);
            this.Controls.Add(this.txtBoxTourist1);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.txtBoxSeats);
            this.Controls.Add(this.txtBoxClientAddress);
            this.Controls.Add(this.txtBoxClientName);
            this.Controls.Add(this.btnPurchase);
            this.Controls.Add(this.btnBack);
            this.Controls.Add(this.labelPurchase);
            this.Name = "PurchaseForm";
            this.Text = "PurchaseForm";
            this.Load += new System.EventHandler(this.PurchaseForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labelPurchase;
        private System.Windows.Forms.Button btnBack;
        private System.Windows.Forms.Button btnPurchase;
        private System.Windows.Forms.TextBox txtBoxClientName;
        private System.Windows.Forms.TextBox txtBoxClientAddress;
        private System.Windows.Forms.TextBox txtBoxSeats;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox txtBoxTourist1;
        private System.Windows.Forms.TextBox txtBoxTourist2;
        private System.Windows.Forms.TextBox txtBoxTourist3;
        private System.Windows.Forms.Label labelError;
        private System.Windows.Forms.Label labelSucces;
    }
}