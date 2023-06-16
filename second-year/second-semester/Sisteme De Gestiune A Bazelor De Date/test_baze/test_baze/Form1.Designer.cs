namespace test_baze
{
    partial class Form1
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
            this.listBoxCategorii = new System.Windows.Forms.ListBox();
            this.dataGridPaturi = new System.Windows.Forms.DataGridView();
            this.btnCommit = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridPaturi)).BeginInit();
            this.SuspendLayout();
            // 
            // listBoxCategorii
            // 
            this.listBoxCategorii.BackColor = System.Drawing.Color.Aquamarine;
            this.listBoxCategorii.FormattingEnabled = true;
            this.listBoxCategorii.ItemHeight = 16;
            this.listBoxCategorii.Location = new System.Drawing.Point(53, 12);
            this.listBoxCategorii.Name = "listBoxCategorii";
            this.listBoxCategorii.Size = new System.Drawing.Size(861, 148);
            this.listBoxCategorii.TabIndex = 0;
            // 
            // dataGridPaturi
            // 
            this.dataGridPaturi.BackgroundColor = System.Drawing.SystemColors.ActiveCaption;
            this.dataGridPaturi.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridPaturi.Location = new System.Drawing.Point(53, 189);
            this.dataGridPaturi.Name = "dataGridPaturi";
            this.dataGridPaturi.RowHeadersWidth = 51;
            this.dataGridPaturi.RowTemplate.Height = 24;
            this.dataGridPaturi.Size = new System.Drawing.Size(861, 137);
            this.dataGridPaturi.TabIndex = 1;
            // 
            // btnCommit
            // 
            this.btnCommit.BackColor = System.Drawing.Color.DarkSlateGray;
            this.btnCommit.FlatStyle = System.Windows.Forms.FlatStyle.System;
            this.btnCommit.Font = new System.Drawing.Font("Calibri", 25.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCommit.ForeColor = System.Drawing.SystemColors.ControlLight;
            this.btnCommit.Location = new System.Drawing.Point(53, 360);
            this.btnCommit.Name = "btnCommit";
            this.btnCommit.Size = new System.Drawing.Size(861, 65);
            this.btnCommit.TabIndex = 2;
            this.btnCommit.Text = "Commit";
            this.btnCommit.UseVisualStyleBackColor = false;
            this.btnCommit.Click += new System.EventHandler(this.btnCommit_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.RoyalBlue;
            this.ClientSize = new System.Drawing.Size(952, 519);
            this.Controls.Add(this.btnCommit);
            this.Controls.Add(this.dataGridPaturi);
            this.Controls.Add(this.listBoxCategorii);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridPaturi)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListBox listBoxCategorii;
        private System.Windows.Forms.DataGridView dataGridPaturi;
        private System.Windows.Forms.Button btnCommit;
    }
}

