namespace Seminar6Exam
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
            this.listBoxCofetarie = new System.Windows.Forms.ListBox();
            this.dataGridViewBriosa = new System.Windows.Forms.DataGridView();
            this.btnCommit = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewBriosa)).BeginInit();
            this.SuspendLayout();
            // 
            // listBoxCofetarie
            // 
            this.listBoxCofetarie.FormattingEnabled = true;
            this.listBoxCofetarie.ItemHeight = 16;
            this.listBoxCofetarie.Location = new System.Drawing.Point(33, 21);
            this.listBoxCofetarie.Name = "listBoxCofetarie";
            this.listBoxCofetarie.Size = new System.Drawing.Size(966, 212);
            this.listBoxCofetarie.TabIndex = 0;
            // 
            // dataGridViewBriosa
            // 
            this.dataGridViewBriosa.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewBriosa.Location = new System.Drawing.Point(33, 248);
            this.dataGridViewBriosa.Name = "dataGridViewBriosa";
            this.dataGridViewBriosa.RowHeadersWidth = 51;
            this.dataGridViewBriosa.RowTemplate.Height = 24;
            this.dataGridViewBriosa.Size = new System.Drawing.Size(966, 151);
            this.dataGridViewBriosa.TabIndex = 1;
            // 
            // btnCommit
            // 
            this.btnCommit.Location = new System.Drawing.Point(33, 415);
            this.btnCommit.Name = "btnCommit";
            this.btnCommit.Size = new System.Drawing.Size(966, 69);
            this.btnCommit.TabIndex = 2;
            this.btnCommit.Text = "Commit";
            this.btnCommit.UseVisualStyleBackColor = true;
            this.btnCommit.Click += new System.EventHandler(this.btnCommit_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1026, 589);
            this.Controls.Add(this.btnCommit);
            this.Controls.Add(this.dataGridViewBriosa);
            this.Controls.Add(this.listBoxCofetarie);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewBriosa)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListBox listBoxCofetarie;
        private System.Windows.Forms.DataGridView dataGridViewBriosa;
        private System.Windows.Forms.Button btnCommit;
    }
}

